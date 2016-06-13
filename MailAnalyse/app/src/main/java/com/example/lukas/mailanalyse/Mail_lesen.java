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
        contact=getIntent().getLongExtra(MainActivity.CONTACTID,0);

        showEmail();

    }

    private void showEmail() {

        Cursor cursorContact =getContentResolver().query(MailAnalyseProvider.CONTACT_URI,null,MailAnalyseProvider.Contact.CONTACTID + "=" + String.valueOf(contact) ,null,MailAnalyseProvider.Contact.CONTACTID);
        von.setText(cursorContact.toString());
    }
}
