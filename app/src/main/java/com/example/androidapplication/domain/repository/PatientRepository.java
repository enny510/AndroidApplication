package com.example.androidapplication.domain.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.model.presenters.PatientWithSessions;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PatientRepository {

    @Insert
    Long insert(Patient model);

    @Update
    int update(Patient model);

    @Transaction @Query("SELECT * FROM patient WHERE id = :id")
    Single<PatientWithSessions> getById(long id);

    @Transaction @Query("SELECT * FROM patient")
    Single<List<PatientWithSessions>> getAll();

    @Delete
    void delete(Patient patient);


}
