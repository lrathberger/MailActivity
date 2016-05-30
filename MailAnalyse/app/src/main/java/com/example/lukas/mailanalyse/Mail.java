package com.example.lukas.mailanalyse;

import android.content.ContentValues;
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

        String mailContent ="jjafdsahfdsahfsdahfsadjf";
        int mailpositiv=1;
        String maildate="12.34.5";
        String mailsubject="test";

        ContentValues values=new ContentValues();
        values.put("content",mailContent);
        values.put("mailpositiv",mailpositiv);
        values.put("maildate",maildate);
        values.put("mailsubject",mailsubject);
        values.put("contact_id",contact);

        getContentResolver().insert(MailAnalyseProvider.MAILS_URI,values);



        showEmails();

    }

    private void showEmails() {
        Cursor cursorPostiv =getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,MailAnalyseProvider.Mails.CONTACTID + "=? AND " + MailAnalyseProvider.Mails.POSITIV + "=1", new String[]{String.valueOf(contact)},MailAnalyseProvider.Mails.MAILSUBJECT);
        Cursor cursorNegativ =getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,MailAnalyseProvider.Mails.CONTACTID + "=? AND " + MailAnalyseProvider.Mails.POSITIV + "=0", new String[]{String.valueOf(contact)},MailAnalyseProvider.Mails.MAILSUBJECT);
        adapterPos=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursorPostiv,new String[]{MailAnalyseProvider.Mails.MAILSUBJECT},new int[]{android.R.id.text1});
        listViewPos.setAdapter(adapterPos);
        adapterNeg=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursorNegativ,new String[]{MailAnalyseProvider.Mails.MAILSUBJECT},new int[]{android.R.id.text1});
        listViewNeg.setAdapter(adapterNeg);

    }
}

