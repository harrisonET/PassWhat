package com.example.passwhat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwhat.EditActivity;
import com.example.passwhat.MainActivity;
import com.example.passwhat.Model.Account;
import com.example.passwhat.R;
import com.google.gson.Gson;
import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{
    private ArrayList<Account>accountList;
    private Context context;
    public AccountAdapter(Context applicationContext, ArrayList<Account> accountList) {
        this.context = applicationContext;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Account account = accountList.get(position);
        holder.text.setText(account.getAccountName());

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                editAccount(account);
                                break;
                            case R.id.menu_delete:
                                MainActivity.accountAdapter.notifyDataSetChanged();
                                MainActivity.accountDB.deleteAccount(account);
                                accountList.remove(account);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    public void editAccount(Account account){
        Intent intent = new Intent(context, EditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("account", (new Gson()).toJson(account));
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public ImageButton imgMenu;

        public ViewHolder(View item){
            super(item);
            text = item.findViewById(R.id.text);
            imgMenu = item.findViewById(R.id.btnMenu);
        }
    }

}
