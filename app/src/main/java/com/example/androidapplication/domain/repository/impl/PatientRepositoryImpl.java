package com.example.androidapplication.domain.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.model.PatientsDatabaseHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.BIRTHDAY_COLUMN_NAME;
import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.ID_COLUMN_NAME;
import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.NAME_COLUMN_NAME;
import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.PATRONUM_COLUMN_NAME;
import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.SURNAME_COLUMN_NAME;
import static com.example.androidapplication.domain.model.PatientsDatabaseHelper.TABLE_NAME;

public class PatientRepositoryImpl{//} implements PatientRepository {

    private Context context;

    public PatientRepositoryImpl(Context context) {
        this.context = context;
    }

   // @Override
    public long insert(Patient model) {
        try(SQLiteDatabase db = new PatientsDatabaseHelper(context).getReadableDatabase()) {
            return PatientsDatabaseHelper.insertPatient(db, model.getName(),
                    model.getSurname(), model.getPatronumic(), model.getBirthDay());
        }
    }

    //@Override
    public int update(Patient model) {
        try(SQLiteDatabase db = new PatientsDatabaseHelper(context).getReadableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(NAME_COLUMN_NAME, model.getName());
            values.put(SURNAME_COLUMN_NAME, model.getSurname());
            values.put(PATRONUM_COLUMN_NAME, model.getPatronumic());
            values.put(BIRTHDAY_COLUMN_NAME, model.getBirthDay().getTime());
            return db.update(TABLE_NAME,
                    values,
                    ID_COLUMN_NAME + " = ?",
                    new String[] {Long.toString(model.getId())});
        }
    }

    //@Override
    public Patient getById(long id) {

        try(SQLiteDatabase db = new PatientsDatabaseHelper(context).getReadableDatabase()) {

            Cursor cursor = db.query(PatientsDatabaseHelper.TABLE_NAME,
                    new String[]{ID_COLUMN_NAME, NAME_COLUMN_NAME, SURNAME_COLUMN_NAME, PATRONUM_COLUMN_NAME,
                            BIRTHDAY_COLUMN_NAME},
                    ID_COLUMN_NAME + " =?",
                    new String[]{String.valueOf(id)},
                    null, null, null);

            if (cursor.moveToFirst())
                return retrievePatientFromCursor(cursor);
            else return null;
        }
    }

    //@Override
    public List<Patient> getAll(){
        List<Patient> result = new ArrayList<>();

        try(SQLiteDatabase db = new PatientsDatabaseHelper(context).getReadableDatabase()) {
            Cursor cursor = db.query(PatientsDatabaseHelper.TABLE_NAME,
                    new String[]{ID_COLUMN_NAME, NAME_COLUMN_NAME, SURNAME_COLUMN_NAME, PATRONUM_COLUMN_NAME,
                            BIRTHDAY_COLUMN_NAME},
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                result.add(retrievePatientFromCursor(cursor));
            }
        }

        return result;
    }

    //@Override
    public void delete(Patient patient) {
        try(SQLiteDatabase db = new PatientsDatabaseHelper(context).getReadableDatabase()) {
            db.delete(PatientsDatabaseHelper.TABLE_NAME,
                    ID_COLUMN_NAME + "=?" ,
                    new String[]{String.valueOf(patient.getId())});
        }
    }

    private Patient retrievePatientFromCursor(Cursor cursor){
        return new Patient(
                cursor.getLong(cursor.getColumnIndex(ID_COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(NAME_COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(SURNAME_COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(PATRONUM_COLUMN_NAME)),
                new Date(cursor.getLong(cursor.getColumnIndex(BIRTHDAY_COLUMN_NAME)))
        );
    }
}
