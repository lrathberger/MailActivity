package com.example.lukas.mailanalyse;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by lukas on 23.05.16.
 */
public class MailAnalyseProvider extends ContentProvider{

    public static final String PROVIDER_NAME = "at.htl_grieskirchen.MailAnalyse";
    public static final String CONTACT_URL = "content://" + PROVIDER_NAME + "/contacts";
    public static final Uri CONTACT_URI = Uri.parse(CONTACT_URL);
    public static final String MAILS_URL = "content://" + PROVIDER_NAME + "/mails";
    public static final Uri MAILS_URI = Uri.parse(MAILS_URL);



    public class Contact{
        public static final String CONTACTNAME= tbl_contact.CONTACT_EMAIL;
        public static final String CONTACTID= tbl_contact.CONTACT_ID;
    }
    public class Mails{
        public static final String MAILID=tbl_mail.MAIL_ID;
        public static final String SUBJECT= tbl_mail.MAIL_SUBJECT;
        public static final String CONTACTID= tbl_mail.CONTACT_ID;
        public static final String POSITIV=tbl_mail.MAIL_POSITIV;
        public static final String CONTENT=tbl_mail.MAIL_CONTENT;
        public static final String DATE=tbl_mail.MAIL_DATE;


    }

    static final int CONTACTS = 1;
    static final int CONTACTS_ID = 2;
    static final int MAILS = 3;
    static final int MAILS_ID = 4;

    static final UriMatcher uriMatcher =  new UriMatcher(UriMatcher.NO_MATCH);

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {

        uriMatcher.addURI(PROVIDER_NAME, "contacts", CONTACTS);
        uriMatcher.addURI(PROVIDER_NAME, "contacts/#", CONTACTS_ID);
        uriMatcher.addURI(PROVIDER_NAME, "mails", MAILS);
        uriMatcher.addURI(PROVIDER_NAME, "mails/#", MAILS_ID);

        db = (new DBHelper(getContext())).getWritableDatabase();


        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor =null;

        switch(uriMatcher.match(uri))
        {
            case CONTACTS:
                cursor= db.query(tbl_contact.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACTS_ID:
                if(selection!=null){
                    throw new UnsupportedOperationException();
                }
                selection= tbl_contact.CONTACT_ID+" = "+uri.getPathSegments().get(1);
                cursor= db.query(tbl_contact.TABLE_NAME,projection,selection,null,null,null,sortOrder);
                break;
            case MAILS:
                cursor= db.query(tbl_mail.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case MAILS_ID:
                if(selection!=null){
                    throw new UnsupportedOperationException();
                }
                selection= tbl_mail.MAIL_ID+" = "+uri.getPathSegments().get(1);
                cursor= db.query(tbl_mail.TABLE_NAME,projection,selection,null,null,null,sortOrder);
                break;
            default:
                break;

        }
        return cursor;


    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){

            case CONTACTS:
                db.insert(tbl_contact.TABLE_NAME,null,values);
                break;

            case MAILS:
                db.insert(tbl_mail.TABLE_NAME,null,values);
                break;
            default:
                throw new UnsupportedOperationException("lg scheiss baut");

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }




}
