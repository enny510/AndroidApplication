package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.repository.PatientRepository;
import com.example.androidapplication.presentation.contracts.EditAddContract;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

public class EditAddPatientPresenter
        extends BasePresenter<EditAddContract.View>
        implements EditAddContract.Presenter {

    private PatientRepository repository;

    public EditAddPatientPresenter(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onSubmit(Patient patient) {
        try {
            if (patient.getId() == null)
                repository.insert(patient);
            else
                repository.update(patient);
            getView().handleSuccess();
        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }
    }

    @Override
    public void onViewIsReady(long id) {
        try{
            Patient p;
            if((p = repository.getById(id)) != null)
                getView().showPatient(p);
            else getView().handleError("Patient with id " + id + "isn't founded");

        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }

    }
}
