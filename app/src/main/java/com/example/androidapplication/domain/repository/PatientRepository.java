package com.example.androidapplication.domain.repository;

import android.arch.persistence.room.*;
import com.example.androidapplication.domain.model.Patient;

import java.util.List;

@Dao
public interface PatientRepository {

    @Insert
    long insert(Patient model);

    @Update
    int update(Patient model);

    @Query("SELECT * FROM patient WHERE id = :id")
    Patient getById(long id);

    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Delete
    void delete(Patient patient);


}
