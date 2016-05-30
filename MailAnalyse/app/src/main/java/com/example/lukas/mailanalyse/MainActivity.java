package com.example.lukas.mailanalyse;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    ListView listViewEmail;
    SimpleCursorAdapter adapterEmail;
    Intent intentContactMail;
    public static final String CONTACTID="contactid";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        listViewEmail=(ListView) findViewById(R.id.listViewEmail);

        listViewEmail.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                intentContactMail=new Intent(MainActivity.this,Mail.class);
                intentContactMail.putExtra(CONTACTID, id);
                startActivity(intentContactMail);
            }
        });

        String emailaddr="lukasrathberger@gmail.com";

        ContentValues values =new ContentValues();
        values.put(MailAnalyseProvider.Contact.CONTACTNAME,emailaddr);

        getContentResolver().insert(MailAnalyseProvider.CONTACT_URI,values);
        showContact();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showContact();
    }

    private void showContact() {
        Cursor cursor =getContentResolver().query(MailAnalyseProvider.CONTACT_URI,null,null,null,MailAnalyseProvider.Contact.CONTACTNAME);
        adapterEmail=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{MailAnalyseProvider.Contact.CONTACTNAME},new int[]{android.R.id.text1});
        listViewEmail.setAdapter(adapterEmail);
    }
}
