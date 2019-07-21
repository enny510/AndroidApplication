package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;
import com.example.androidapplication.domain.model.AppDatabase;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

public class ViewPatientDetailsPresenter
        extends BasePresenter<ViewPatientDetailContract.View>
        implements ViewPatientDetailContract.Presenter {

    private AppDatabase db;

    public ViewPatientDetailsPresenter(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void onViewIsReady(long id) {
        try{
            Patient p;
            if((p = db.patientDao().getById(id)) != null)
                getView().showPatient(p);
            else getView().handleError("Patient with id " + id + "isn't founded");

        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }

    }
}
