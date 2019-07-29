package com.example.androidapplication.domain.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.database.SQLException;
import android.support.annotation.NonNull;

import com.example.androidapplication.domain.model.converters.DateConverter;
import com.example.androidapplication.domain.repository.PatientRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.Executors;

@Database(entities = {Patient.class, Session.class}, version = 2)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientRepository patientDao();

    public static Callback createCallback = new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        String columns = "name, surname, patronum, birthday, diagnosis, enter_date, out_date, is_paid";
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            try{
                                db.execSQL("INSERT INTO Patient (" + columns + ") VALUES (" +
                                        "'Сергей', 'Иванов', 'Васильевич', " +
                                        new Date(1990,1,13).getTime() +
                                        ", 'не определен'," +
                                        new Date(2017,2,12).getTime() + "," +
                                        new Date(2017,2,31).getTime() + ", 1)");
                                db.execSQL("INSERT INTO Patient (" + columns + ") VALUES (" +
                                        "'Юлия', 'Савушкина', 'Прокофьевна', "+
                                        new Date(1983,10,1).getTime() +
                                        ", 'ангина', " +
                                        new Date(2017,11,6).getTime() + "," +
                                        new Date(2017,11,20).getTime()+ ", 0)");
                                db.execSQL("INSERT INTO Patient (" + columns + ") VALUES (" +
                                        "'Артем', 'Золотой', 'Викторович', "+
                                        new Date(1991,2,31).getTime() +
                                        ", 'перелом кисти', " +
                                        new Date(2017,9,18).getTime() + ", NULL, 1)");
                                db.execSQL("INSERT INTO Patient (" + columns + ") VALUES (" +
                                        "'Евгения', 'Рай', 'Александровна', "+
                                        new Date(1990,12,12).getTime() +
                                        ", 'нефроптоз', " +
                                        new Date(2017,9,3).getTime() + ", NULL, 0)");
                            }
                            catch(SQLException e){
                                e.printStackTrace();
                            }
                            });
                    }
                };


    public static Migration[] migrations = new Migration[]{new Migration(1, 2) {
        @Override
        public void migrate(@NotNull final SupportSQLiteDatabase database) {
            try{
                database.execSQL("CREATE TABLE Session(" +
                        "   id INTEGER," +
                        "   patient_id INTEGER NOT NULL," +
                        "   diagnosis TEXT NOT NULL ," +
                        "   enter_date INTEGER NOT NULL," +
                        "   out_date INTEGER," +
                        "   is_paid INTEGER DEFAULT 0 NOT NULL," +
                        "   PRIMARY KEY( id )," +
                        "   FOREIGN KEY (patient_id) REFERENCES Patient(id));");

                database.execSQL("INSERT INTO Session (patient_id, diagnosis, enter_date, out_date, is_paid) " +
                        "SELECT id AS patient_id, diagnosis, enter_date, out_date, is_paid " +
                        "FROM Patient;");

                database.execSQL("CREATE TABLE Patient_temp(" +
                        "   id INTEGER," +
                        "   name TEXT NOT NULL," +
                        "   surname TEXT NOT NULL," +
                        "   patronum TEXT," +
                        "   birthday INTEGER NOT NULL," +
                        "   PRIMARY KEY( id ));");

                database.execSQL("INSERT INTO Patient_temp (name, surname, patronum, birthday)" +
                        "SELECT name, surname, patronum, birthday " +
                        "FROM Patient;");

                database.execSQL("DROP TABLE Patient;");

                database.execSQL("ALTER TABLE Patient_temp RENAME TO Patient");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }};
}
