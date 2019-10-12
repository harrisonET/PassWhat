package com.example.passwhat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passwhat.Model.Account;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnFocusChangeListener{

    EditText accEditTxt,unameEditTxt,passEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        accEditTxt = findViewById(R.id.accEditTxt);
        unameEditTxt = findViewById(R.id.unameEditTxt);
        passEditTxt = findViewById(R.id.passEditTxt);

        accEditTxt.setOnFocusChangeListener(this);
        unameEditTxt.setOnFocusChangeListener(this);
        passEditTxt.setOnFocusChangeListener(this);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void addNew(View view){
        String acc = accEditTxt.getText().toString();
        String uname = unameEditTxt.getText().toString();
        String pass = passEditTxt.getText().toString();


        if (accountChecker(acc,uname,pass) == true) {
            //insert into database sql
            MainActivity.accountDB.addActivity(acc,uname,pass);
            MainActivity.accountDB.displayAccount();
            Toast.makeText(getApplicationContext(), "Accout  for " + acc + " is created!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public boolean accountChecker(String acc, String uname, String pass){
        boolean ok = false;
        String warning = "Fileds cannot be empty, must be all filled!";
        if (acc.isEmpty() || uname.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), warning, Toast.LENGTH_LONG).show();
        }
        else
            ok = true;

        return ok;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            hideKeyboard(v);
        }
    }
}
