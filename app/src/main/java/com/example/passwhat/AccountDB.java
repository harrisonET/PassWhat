package com.example.passwhat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.example.passwhat.Model.Account;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AccountDB {
    static SQLiteDatabase eventsDB;
    static Context context;

    public AccountDB(Context context){
        this.context = context;
        eventsDB = context.openOrCreateDatabase("AccountDB", MODE_PRIVATE, null);
        eventsDB.execSQL("CREATE TABLE IF NOT EXISTS Account (accountname VARCHAR ,username VARCHAR, password VARCHAR,  id INTEGER PRIMARY KEY)");
    }

    public void displayAccount(){
        Cursor c = this.eventsDB.rawQuery("SELECT * FROM Account", null);
        int aIndex = c.getColumnIndex("accountname");
        int uIndex = c.getColumnIndex("username");
        int pIndex = c.getColumnIndex("password");
        int idIndex = c.getColumnIndex("id");
        c.moveToFirst();
        try {
            while (c != null) {
                Log.i("Account name: ", c.getString(aIndex));
                Log.i("Username: ", c.getString(uIndex));
                Log.i("Pwd: ", c.getString(pIndex));
                Log.i("Id: ", Integer.toString(c.getInt(idIndex)));

                if (c != null)
                    c.moveToNext();
                else
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addActivity(String aname, String uname, String pwd){
        String sql = "INSERT INTO Account (accountname,username,password) VALUES (?, ?, ?)";
        SQLiteStatement statement = eventsDB.compileStatement(sql);
        try {
            statement.bindString(1, aname);
            statement.bindString(2, uname);
            statement.bindString(3, pwd);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Account> getAccount(){
        ArrayList<Account>result = new ArrayList<Account>();
        Cursor c = this.eventsDB.rawQuery("SELECT * FROM Account", null);
        int aIndex = c.getColumnIndex("accountname");
        int uIndex = c.getColumnIndex("username");
        int pIndex = c.getColumnIndex("password");
        int idIndex = c.getColumnIndex("id");
        c.moveToFirst();
        try {
            while (c != null) {
                Account account = new Account(c.getString(aIndex), c.getString(uIndex),c.getString(pIndex) );
                result.add(account);
                //             Log.i("Activity: ", c.getString(activityIndex));
                //               Log.i("Credit: ", Integer.toString(c.getInt(creditIndex)));
                //              Log.i("Id: ", Integer.toString(c.getInt(idIndex)));
                if (c != null)
                    c.moveToNext();
                else
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    public void deleteAccount(Account account){
        String sql = "DELETE FROM Account WHERE accountname=?";
        SQLiteStatement statement = eventsDB.compileStatement(sql);
        statement.bindString(1, account.getAccountName());
        statement.execute();
    }

    public void deleteAllInDB(){
        eventsDB.execSQL("DELETE FROM Account");
    }

    public void editAccount(Account prevAcc, String newAcc, String uname, String pass){

        String sql = "UPDATE Account SET accountname=?, username=?, password=? WHERE accountname=?";
        SQLiteStatement statement = eventsDB.compileStatement(sql);
        try{
            statement.bindString(1, newAcc);
            statement.bindString(2, uname);
            statement.bindString(3, pass);
            statement.bindString(4, prevAcc.getAccountName());
            statement.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context,"Successfully edited!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
