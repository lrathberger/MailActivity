package com.example.lukas.mailanalyse;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by lrathberger on 03.06.2016.
 */
public class Mail_lesen extends AppCompatActivity {

    private long mail;
    private long contact;
    private TextView von;
    private TextView betreff;
    private TextView inhalt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_lesen);
        von=(TextView)findViewById(R.id.editTextVon);
        betreff=(TextView)findViewById(R.id.editTextBetreff);
        inhalt=(TextView)findViewById(R.id.editTextInhalt);


        mail=getIntent().getLongExtra(Mail.MAILID,0);
        Log.d("TEST",mail+"");
        contact=getIntent().getLongExtra(MainActivity.CONTACTID,0);

        showEmail();

    }

    private void showEmail() {

        Cursor cursorContact =getContentResolver().query(MailAnalyseProvider.CONTACT_URI,new String[]{MailAnalyseProvider.Contact.CONTACTNAME},MailAnalyseProvider.Contact.CONTACTID + "=" + String.valueOf(contact) ,null,null);
        cursorContact.moveToFirst();
         Log.d("TEST",cursorContact.getString(cursorContact.getColumnIndex(MailAnalyseProvider.Contact.CONTACTNAME)));
        von.setText(cursorContact.getString(cursorContact.getColumnIndex(MailAnalyseProvider.Contact.CONTACTNAME)));

        Cursor cursorBetreff =getContentResolver().query(MailAnalyseProvider.MAILS_URI,new String[] {MailAnalyseProvider.Mails.SUBJECT  },MailAnalyseProvider.Mails.CONTACTID + "=" + String.valueOf(contact) + " AND " + MailAnalyseProvider.Mails.MAILID + "=" + String.valueOf(mail) ,null,null);
        cursorBetreff.moveToFirst();
        Log.d("TEST",cursorBetreff.getString(cursorBetreff.getColumnIndex(MailAnalyseProvider.Mails.SUBJECT)));
        betreff.setText(cursorBetreff.getString(cursorBetreff.getColumnIndex(MailAnalyseProvider.Mails.SUBJECT)));

        Cursor cursorInhalt =getContentResolver().query(MailAnalyseProvider.MAILS_URI,new String[] {MailAnalyseProvider.Mails.CONTENT  },MailAnalyseProvider.Mails.CONTACTID + "=" + String.valueOf(contact) + " AND " + MailAnalyseProvider.Mails.MAILID + "=" + String.valueOf(mail),null,null);
        cursorInhalt.moveToFirst();
        Log.d("TEST",cursorInhalt.getString(cursorInhalt.getColumnIndex(MailAnalyseProvider.Mails.CONTENT)));
        inhalt.setText(cursorInhalt.getString(cursorInhalt.getColumnIndex(MailAnalyseProvider.Mails.CONTENT)));



    }
}
