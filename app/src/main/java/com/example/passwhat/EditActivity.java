package com.example.passwhat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwhat.Model.Account;
import com.google.gson.Gson;

public class EditActivity extends AppCompatActivity {

    EditText accEditTxt,unameEditTxt,passEditTxt;
    Account prevAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        accEditTxt = findViewById(R.id.accEditTxt);
        unameEditTxt = findViewById(R.id.unameEditTxt);
        passEditTxt = findViewById(R.id.passEditTxt);
        String accStr = getIntent().getStringExtra("account");
        prevAcc =  new Gson().fromJson(accStr,Account.class);;

        accEditTxt.setText(prevAcc.getAccountName());
        unameEditTxt.setText(prevAcc.getUsername());
        passEditTxt.setText(prevAcc.getPassword());

    }

    public void editAccount(View view){
        String acc = accEditTxt.getText().toString();
        String uname = unameEditTxt.getText().toString();
        String pass = passEditTxt.getText().toString();

        MainActivity.accountDB.editAccount(prevAcc,acc,uname,pass);

    }



}
