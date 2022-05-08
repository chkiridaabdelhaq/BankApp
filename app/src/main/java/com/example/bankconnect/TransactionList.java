package com.example.bankconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bankconnect.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class TransactionList extends BaseActivity {

    private TextView textEmail;
    private ListView listViewTransaction;
    private FloatingActionButton faMessage;
    private FloatingActionButton faMap;
    DbHelper db;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        setContentView(R.layout.activity_transaction_list);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_EMAIL);

        //textEmail = (TextView) findViewById(R.id.textViewEmail);
        //textEmail.setText(message);

        faMessage = findViewById(R.id.floatingActionMessage);
        faMessage.setOnClickListener(messageClick);

        faMap = findViewById(R.id.floatingActionLocal);
        faMap.setOnClickListener(mapClick);

        listViewTransaction = (ListView) findViewById(R.id.listViewTransaction);
        TransactionAdapter adapter = new TransactionAdapter(TransactionList.this, transactionsList());
        listViewTransaction.setAdapter(adapter);
        listViewTransaction.setOnItemClickListener(onItemClickListener);

        db = new DbHelper(TransactionList.this);



    }

    public ArrayList<Transaction> transactionsList(){

        ArrayList<Transaction> list= new ArrayList<Transaction>();
        return db.getAll();
    }



    public void addList(){


        Transaction t1 = new Transaction(R.drawable.tmoney,"Virement","10000","26-04-2022","4124815454","V120","6000");
        db.addTransaction(t1);

        Transaction t2 = new Transaction(R.drawable.tcheque,"Encaissement d'un chèque","9000","10-02-2023","41248985454","V220","6700");
        db.addTransaction(t2);

        Transaction t3 = new Transaction(R.drawable.tonline,"Paiement en ligne","8000","13-02-2023","89548985454","V230","75000");
        db.addTransaction(t3);

        Transaction t4 = new Transaction(R.drawable.tviresement,"Virement","500","13-02-2023","89548985454","V230","75000");
        db.addTransaction(t4);

        Transaction t5 = new Transaction(R.drawable.tcredit,"Crédit","90000","13-02-2023","89548985454","V230","65000");
        db.addTransaction(t5);

        Transaction t6 = new Transaction(R.drawable.tvenligne,"Virement en ligne","800","13-02-2023","89548985454","V230","5700");
        db.addTransaction(t6);

    }



    AdapterView.OnItemClickListener   onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Transaction trs = (Transaction) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(getApplicationContext(),TransactionDetails.class);
            intent.putExtra("transaction", trs.getId());
            startActivity(intent);

        }
    };




    View.OnClickListener messageClick= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

              sendMsg();

        }
    };


    View.OnClickListener mapClick= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(TransactionList.this,"Map",Toast.LENGTH_SHORT).show();

            Intent intentTrs = new Intent(getApplicationContext(),MapActivity.class);
            startActivity(intentTrs);

        }
    };

    public void sendMsg(){


        Dialog dialog= new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.layout_sendmessage);

        EditText editDialogSendMessage= dialog.findViewById(R.id.editDialogSendMessage);
        Button btnSendMessage= dialog.findViewById(R.id.buttonSendMessage);
        Button btnCancel= dialog.findViewById(R.id.buttonSendMessageCancel);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone="0649178934";
                String message=editDialogSendMessage.getText().toString();

                if(!message.isEmpty()){


                    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){

                        /* send automatically*/
                        /*  SmsManager sm = SmsManager.getDefault();
                         sm.sendTextMessage(phone,null,message,null,null); */

                        /* Update before send message option*/
                        Intent sms=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+phone));
                        sms.putExtra("sms_body", message);
                        startActivity(sms);


                        Toast.makeText(TransactionList.this,"Successful, message was sent",Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }




                }else {

                    Toast.makeText(TransactionList.this,"Please type a message",Toast.LENGTH_SHORT).show();
                }

            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.show();




    }




}