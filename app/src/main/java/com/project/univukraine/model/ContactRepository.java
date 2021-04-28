package com.project.univukraine.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository extends SQLiteOpenHelper {
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS contacts (id INTEGER, name TEXT, surname TEXT, phoneNum TEXT, email TEXT)";
    private static final String DATABASE_TABLE = "contacts";
    private static final String DATABASE_NAME = "app";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_PHONENUM = "phoneNum";
    private static final String KEY_EMAIL = "email";

    private static final String TAG = "app";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;//= getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);;
    private UniversityRepository repository;

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ContactRepository(Context context, SQLiteDatabase db) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    private Contacts mapToContacts(Cursor mCursor) {
        Contacts contacts = new Contacts();
        contacts.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ID)));
        contacts.setName(mCursor.getString(mCursor.getColumnIndex(KEY_NAME)));
        contacts.setSurname(mCursor.getString(mCursor.getColumnIndex(KEY_SURNAME)));
        contacts.setPhoneNum(mCursor.getString(mCursor.getColumnIndex(KEY_PHONENUM)));
        contacts.setEmail(mCursor.getString(mCursor.getColumnIndex(KEY_EMAIL)));
        return contacts;
    }

    //---retrieves a particular contact---

    public SQLiteDatabase getDb(SQLiteDatabase db) {
        return this.db = db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public List<Contacts> getAllContacts() throws SQLException {
        //db.
        //open();
        Cursor mCursor = db.query(true, DATABASE_TABLE,
                new String[]{KEY_ID, KEY_NAME, KEY_SURNAME, KEY_PHONENUM, KEY_EMAIL},
                null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(mapToContacts(mCursor));
        while (mCursor.moveToNext()) {
            contactsList.add(mapToContacts(mCursor));
        }
        return contactsList;
    }
    public List<Contacts> getContactFilter() throws SQLException {
        //db.
        //open();
        String sqlQuery = "SELECT* FROM contacts WHERE surname LIKE 'Т%'";
        Cursor mCursor = db.rawQuery(sqlQuery, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(mapToContacts(mCursor));
        while (mCursor.moveToNext()) {
            contactsList.add(mapToContacts(mCursor));
        }
        return contactsList;
    }

}
