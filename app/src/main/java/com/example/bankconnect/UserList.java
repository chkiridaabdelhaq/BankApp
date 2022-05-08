package com.example.bankconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankconnect.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Date;

public class UserList extends BaseActivity {

    private TextView textEmail;
    private ListView adapterList;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        setContentView(R.layout.activity_user_list);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE_EMAIL);
        // textEmail = (TextView) findViewById(R.id.textViewEmail);
        // textEmail.setText(message);

        adapterList = (ListView) findViewById(R.id.listViewUser);

        UserAdapter usersAdapter = new UserAdapter(UserList.this,usersList());

        adapterList.setAdapter(usersAdapter);
        /*GOTO */
       //binding.


    }


    public ArrayList<User> usersList(){

        ArrayList<User> list= new ArrayList<User>();

        User user1 = new User(1,"Abdelhaq","Chkirida","abdo@gmail.com",R.drawable.a,"123456",new Date(),"Admin");
        list.add(user1);
        User user2 = new User(2,"ahmed","Chkirida","ahmed@gmail.com",R.drawable.b,"123456",new Date(),"Admin");
        list.add(user2);
        User user3 = new User(3,"ali","Chkirida","ali@gmail.com",R.drawable.c,"123456",new Date(),"Admin");
        list.add(user3);
        User user4 = new User(4,"yassin","Chkirida","yassin@gmail.com",R.drawable.f,"123456",new Date(),"Admin");
        list.add(user4);
        User user5 = new User(5,"Kaoula","Chkirida","khaoula@gmail.com",R.drawable.e,"123456",new Date(),"Admin");
        list.add(user5);

     return list;
    }



}