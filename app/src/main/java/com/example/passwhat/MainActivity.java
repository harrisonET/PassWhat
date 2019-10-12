package com.example.passwhat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.passwhat.Adapter.AccountAdapter;
import com.example.passwhat.Model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity  {

    FloatingActionButton fab;
    public static AccountDB accountDB;
    public static ArrayList<Account> accountList;
    private RecyclerView recyclerView;
    public static AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountDB = new AccountDB(getApplicationContext());
        accountDB.displayAccount();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        accountList = new ArrayList<>();
        accountList = accountDB.getAccount();
        accountAdapter = new AccountAdapter(getApplicationContext(),accountList);
        recyclerView.setAdapter(accountAdapter);
        accountAdapter.notifyDataSetChanged();
        fab = findViewById(R.id.fabAddAcc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
