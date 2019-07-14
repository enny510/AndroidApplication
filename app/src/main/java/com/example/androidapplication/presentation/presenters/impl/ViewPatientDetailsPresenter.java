package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.repository.PatientRepository;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

public class ViewPatientDetailsPresenter
        extends BasePresenter<ViewPatientDetailContract.View>
        implements ViewPatientDetailContract.Presenter {

    private PatientRepository repository;

    public ViewPatientDetailsPresenter(PatientRepository repository) {
        this.repository = repository;
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
