package com.example.lukas.mailanalyse;

/**
 * Created by lukas on 20.05.16.
 */

import android.util.Log;

import java.util.Properties;

import javax.mail.*;

public class ReadingEmail {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "yourEmailId@gmail.com", "password");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
                Log.d("FROM ADDRESS", "FROM:" + address.toString());
            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            Log.d("SENT DATE","SENT DATE:" + msg.getSentDate());
            Log.d("SUBJECT","SUBJECT:" + msg.getSubject());
            Log.d("CONTENT","CONTENT:" + bp.getContent());
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
