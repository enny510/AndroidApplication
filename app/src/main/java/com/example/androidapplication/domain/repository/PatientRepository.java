package com.example.androidapplication.domain.repository;

import com.example.androidapplication.domain.model.Patient;

import java.util.List;

public interface PatientRepository {

    boolean insert(Patient model);

    boolean update(Patient model);

    Patient getById(long id);

    List<Patient> getAll();

    void delete(Patient patient);
}
