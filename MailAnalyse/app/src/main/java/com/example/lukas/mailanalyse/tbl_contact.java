package com.example.lukas.mailanalyse;

/**
 * Created by lukas on 20.05.16.
 */
public class tbl_contact {

    public static final String TABLE_NAME="Contact";
    public static final String CONTACT_ID="_id";
    public static final String CONTACT_EMAIL="contact_email";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + CONTACT_ID + " INTEGER PRIMARY KEY," +
                    CONTACT_EMAIL + " TEXT NOT NULL);";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
