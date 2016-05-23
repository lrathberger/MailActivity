package com.example.lukas.mailanalyse;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by lukas on 23.05.16.
 */
public class Mail extends AppCompatActivity
{
    private long contact;
    private ListView listViewPos;
    private ListView listViewNeg;
    SimpleCursorAdapter adapterPos;
    SimpleCursorAdapter adapterNeg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        contact=getIntent().getLongExtra(MainActivity.CONTACTID,0);
        listViewPos=(ListView)findViewById(R.id.listViewPositiv);
        listViewNeg=(ListView)findViewById(R.id.listViewNegativ);
        showEmails();

    }

    private void showEmails() {
        Cursor cursor =getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,MailAnalyseProvider.Mails.CONTACTID + "=?", new String[]{String.valueOf(contact)},MailAnalyseProvider.Mails.MAILSUBJECT);

    }
}

