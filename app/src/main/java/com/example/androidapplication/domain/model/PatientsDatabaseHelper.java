package com.example.androidapplication.domain.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


public class PatientsDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "patientsBase";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "patients";

    public static final String ID_COLUMN_NAME = "_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String SURNAME_COLUMN_NAME = "surname";
    public static final String PATRONUM_COLUMN_NAME = "patronum";
    public static final String BIRTHDAY_COLUMN_NAME = "birthday";
//    public static final String CARD_NUMBER_COLUMN_NAME = "card_number";
//    public static final String DIAGNOSIS_COLUMN_NAME = "diagnosis";
//    public static final String IS_PAID_COLUMN_NAME = "is_paid";
//    public static final String ENTER_DATE_COLUMN_NAME = "enter_date";
//    public static final String OUT_DATE_COLUMN_NAME = "out_date";

    public PatientsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COLUMN_NAME+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COLUMN_NAME + " TEXT , "
                + SURNAME_COLUMN_NAME + " TEXT , "
                + PATRONUM_COLUMN_NAME + " TEXT , "
                + BIRTHDAY_COLUMN_NAME + " INTEGER )");
//                + CARD_NUMBER_COLUMN_NAME + " INTEGER ,  "
//                + DIAGNOSIS_COLUMN_NAME + " TEXT,"
//                + IS_PAID_COLUMN_NAME + " INTEGER,"
//                + ENTER_DATE_COLUMN_NAME + " INTEGER,"
//                + OUT_DATE_COLUMN_NAME + " INTEGER)

        insertPatient(db, "Сергей", "Иванов", "Васильевич",  new Date(1990,1,13));
        insertPatient(db, "Юлия", "Савушкина", "Прокофьевна", new Date(1983,10,1));
        insertPatient(db, "Артем", "Золотой", "Викторович", new Date(1991,2,31));
        insertPatient(db, "Евгения", "Рай", "Александровна", new Date(1990,12,12));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static long insertPatient(SQLiteDatabase db, String name, String surname, String patronumic,
            Date birthDay) {
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN_NAME, name);
        values.put(SURNAME_COLUMN_NAME, surname);
        values.put(PATRONUM_COLUMN_NAME, patronumic);
        values.put(BIRTHDAY_COLUMN_NAME, birthDay.getTime());
        return db.insertOrThrow(TABLE_NAME, null, values);
    }
}
