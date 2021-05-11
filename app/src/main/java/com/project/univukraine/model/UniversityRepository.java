package com.project.univukraine.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UniversityRepository extends SQLiteOpenHelper {
    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS university (id INTEGER, name TEXT, address TEXT, studentAmount INTEGER, webometrix TEXT)";
    private static final String DATABASE_TABLE = "university";
    private static final String DATABASE_NAME = "app";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_STUDENT_AMOUNT = "studentAmount";
    private static final String KEY_WEBOMETRIX = "webometrix";
    private static final String KEY_EXCELLENCE = "excellence";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

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

    public UniversityRepository(Context context, SQLiteDatabase db) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS university");
        onCreate(db);
    }

    //---opens the database---
    public UniversityRepository open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    //---insert a contact into the database---
    public long insertUniversity(University university) {
        ContentValues initialValues = new ContentValues();
        //initialValues.put(KEY_ID, university.getId());
        initialValues.put(KEY_ADDRESS, university.getAddress());
        initialValues.put(KEY_NAME, university.getName());
        initialValues.put(KEY_STUDENT_AMOUNT, university.getStudentAmount());
        initialValues.put(KEY_WEBOMETRIX, university.getWebometrix());
        initialValues.put(KEY_EXCELLENCE, university.getExcellence());
        initialValues.put(KEY_LONGITUDE, university.getLatitude());
        initialValues.put(KEY_LATITUDE, university.getLatitude());
        return db.insert(DATABASE_TABLE, null, initialValues);
    }


    public boolean deleteUniversity(long id) {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + id, null) > 0;
    }

    //---retrieves all the contacts---
    public University getUniversityById(int id) {
        Cursor mCursor = db.query(DATABASE_TABLE,
                new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_STUDENT_AMOUNT, KEY_WEBOMETRIX, KEY_EXCELLENCE, KEY_LONGITUDE, KEY_LATITUDE},
                KEY_ID + "=" + id, null, null, null, null);
        mCursor.moveToFirst();
        return mapToUniversity(mCursor);
    }

    private University mapToUniversity(Cursor mCursor) {
        University university = new University();
        university.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ID)));
        university.setName(mCursor.getString(mCursor.getColumnIndex(KEY_NAME)));
        university.setAddress(mCursor.getString(mCursor.getColumnIndex(KEY_ADDRESS)));
        university.setStudentAmount(mCursor.getInt(mCursor.getColumnIndex(KEY_STUDENT_AMOUNT)));
        university.setWebometrix(mCursor.getInt(mCursor.getColumnIndex(KEY_WEBOMETRIX)));
        university.setExcellence(mCursor.getInt(mCursor.getColumnIndex(KEY_EXCELLENCE)));
        university.setLongitude(mCursor.getString(mCursor.getColumnIndex(KEY_LONGITUDE)));
        university.setLatitude(mCursor.getString(mCursor.getColumnIndex(KEY_LATITUDE)));
        return university;
    }

    //---retrieves a particular contact---

    public SQLiteDatabase getDb(SQLiteDatabase db) {
        return this.db = db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public List<University> getAllUniversities() throws SQLException {
        //db.
        //open();
        Cursor mCursor = db.query(true, DATABASE_TABLE,
                new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_STUDENT_AMOUNT, KEY_WEBOMETRIX, KEY_EXCELLENCE, KEY_LONGITUDE, KEY_LATITUDE},
                null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        List<University> universityList = new ArrayList<>();
        universityList.add(mapToUniversity(mCursor));
        while (mCursor.moveToNext()) {
            universityList.add(mapToUniversity(mCursor));
        }
        return universityList;
    }

    public List<University> getUniversitiesWithExcellence() throws SQLException {
        //db.
        //open();
        Cursor mCursor = db.query(true, DATABASE_TABLE,
                new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_STUDENT_AMOUNT, KEY_WEBOMETRIX, KEY_EXCELLENCE, KEY_LONGITUDE, KEY_LATITUDE},
                KEY_EXCELLENCE + "< 5000"+" AND "+KEY_ADDRESS+"!= \"Київ\"" , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        List<University> universityList = new ArrayList<>();
        universityList.add(mapToUniversity(mCursor));
        while (mCursor.moveToNext()) {
            universityList.add(mapToUniversity(mCursor));
        }
        return universityList;
    }
    public Integer getWebometrixMinRank() throws SQLException {
        //db.
        //open();
        String sqlQuery = "SELECT MIN(webometrix) AS MinRank FROM university";
        Cursor mCursor = db.rawQuery(sqlQuery, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor.getInt( mCursor.getColumnIndex("MinRank"));
    }
    public Integer getWebometrixMaxRank() throws SQLException {
        //db.
        //open();
        String sqlQuery = "SELECT MAX(webometrix) AS MaxRank FROM university";
        Cursor mCursor = db.rawQuery(sqlQuery, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor.getInt( mCursor.getColumnIndex("MaxRank"));
    }
}
