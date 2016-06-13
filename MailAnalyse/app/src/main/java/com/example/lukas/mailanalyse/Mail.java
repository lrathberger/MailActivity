package com.example.lukas.mailanalyse;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    public static final String MAILID="mailid";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        contact=getIntent().getLongExtra(MainActivity.CONTACTID,0);
        Log.d("contact_id", contact + "");
        listViewPos=(ListView)findViewById(R.id.listViewPositiv);
        listViewPos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mailLesen=new Intent(Mail.this,Mail_lesen.class);
                mailLesen.putExtra(MAILID,id);
                mailLesen.putExtra(MainActivity.CONTACTID,contact);
                startActivity(mailLesen);

            }
        });
        listViewNeg=(ListView)findViewById(R.id.listViewNegativ);
        listViewNeg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mailLesen=new Intent(Mail.this,Mail_lesen.class);
                mailLesen.putExtra(MAILID,id);
                mailLesen.putExtra(MainActivity.CONTACTID,contact);
                startActivity(mailLesen);

            }
        });

        Cursor cursorTest=getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,null,null,null);
        if(!cursorTest.moveToFirst()) {


            String mailContent = "jjafdsahfdsahfsdahfsadjf";
            int mailpositiv = 1;
            String maildate = "12.34.5";
            String mailsubject = "test";
            int contactdID=1;

            ContentValues values = new ContentValues();
            values.put(MailAnalyseProvider.Mails.CONTENT, mailContent);
            values.put(MailAnalyseProvider.Mails.POSITIV, mailpositiv);
            values.put(MailAnalyseProvider.Mails.DATE, maildate);
            values.put(MailAnalyseProvider.Mails.SUBJECT, mailsubject);
            values.put(MailAnalyseProvider.Mails.CONTACTID, contactdID);

            getContentResolver().insert(MailAnalyseProvider.MAILS_URI, values);

            String mailContent2 = "jjafdsahfdsahfsdahfsadjf1";
            int mailpositiv2 = 0;
            String maildate2 = "12.34.5";
            String mailsubject2 = "test1";

            ContentValues values2 = new ContentValues();
            values2.put(MailAnalyseProvider.Mails.CONTENT, mailContent2);
            values2.put(MailAnalyseProvider.Mails.POSITIV, mailpositiv2);
            values2.put(MailAnalyseProvider.Mails.DATE, maildate2);
            values2.put(MailAnalyseProvider.Mails.SUBJECT, mailsubject2);
            values2.put(MailAnalyseProvider.Mails.CONTACTID,contactdID);

            getContentResolver().insert(MailAnalyseProvider.MAILS_URI, values2);

            String mailContent3 = "jjafdsahfdsahfsdahfsadj3f";
            int mailpositiv3 = 1;
            String maildate3 = "12.34.5";
            String mailsubject3 = "test3";
            int contactdID3=2;

            ContentValues values3 = new ContentValues();
            values3.put(MailAnalyseProvider.Mails.CONTENT, mailContent3);
            values3.put(MailAnalyseProvider.Mails.POSITIV, mailpositiv3);
            values3.put(MailAnalyseProvider.Mails.DATE, maildate3);
            values3.put(MailAnalyseProvider.Mails.SUBJECT, mailsubject3);
            values3.put(MailAnalyseProvider.Mails.CONTACTID, contactdID3);

            getContentResolver().insert(MailAnalyseProvider.MAILS_URI, values3);

            showEmails();
        }


        showEmails();

    }

    private void showEmails() {
        Cursor cursorPostiv =getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,MailAnalyseProvider.Mails.CONTACTID + "=" + String.valueOf(contact) + " AND " + MailAnalyseProvider.Mails.POSITIV + "=1",null,MailAnalyseProvider.Mails.CONTACTID);
        Cursor cursorNegativ =getContentResolver().query(MailAnalyseProvider.MAILS_URI,null,MailAnalyseProvider.Mails.CONTACTID + "=" + String.valueOf(contact) + " AND " + MailAnalyseProvider.Mails.POSITIV + "=0",null,MailAnalyseProvider.Mails.CONTACTID);
        adapterPos=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursorPostiv,new String[]{MailAnalyseProvider.Mails.SUBJECT},new int[]{android.R.id.text1});
        listViewPos.setAdapter(adapterPos);
        adapterNeg=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursorNegativ,new String[]{MailAnalyseProvider.Mails.SUBJECT},new int[]{android.R.id.text1});
        listViewNeg.setAdapter(adapterNeg);

    }
}

