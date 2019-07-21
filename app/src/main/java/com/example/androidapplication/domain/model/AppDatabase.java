package com.example.androidapplication.domain.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.example.androidapplication.domain.model.converters.DateConverter;
import com.example.androidapplication.domain.repository.PatientRepository;

@Database(entities = {Patient.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientRepository patientDao();
}
