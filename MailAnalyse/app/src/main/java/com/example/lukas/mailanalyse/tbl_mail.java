package com.example.lukas.mailanalyse;

/**
 * Created by lukas on 20.05.16.
 */
public class tbl_mail {

    public static final String TABLE_NAME="Mail";
    public static final String MAIL_ID="_id";
    public static final String MAIL_CONTENT ="mail_content";
    public static final String CONTACT_ID="contact_id";
    public static final String MAIL_DATE="mail_date";
    public static final String MAIL_SUBJECT="mail_subject";


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + MAIL_ID + " INTEGER PRIMARY KEY," +
                    MAIL_CONTENT + " TEXT NOT NULL, " +
                    MAIL_DATE + " TEXT, " +
                    MAIL_SUBJECT + " TEXT " +
                    CONTACT_ID + " INT NOT NULL);";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



}
