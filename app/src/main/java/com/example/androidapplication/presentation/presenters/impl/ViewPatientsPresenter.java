package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;
import com.example.androidapplication.domain.repository.PatientRepository;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

public class ViewPatientsPresenter
        extends BasePresenter<ViewPatientsContract.View>
        implements ViewPatientsContract.Presenter {

    private PatientRepository repository;

    public ViewPatientsPresenter(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onViewIsReady() {
        try{
            getView().showPatients(repository.getAll());
        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }

    }
}
