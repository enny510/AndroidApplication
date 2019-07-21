package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;
import com.example.androidapplication.domain.model.AppDatabase;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

public class ViewPatientsPresenter
        extends BasePresenter<ViewPatientsContract.View>
        implements ViewPatientsContract.Presenter {

    private AppDatabase db;

    public ViewPatientsPresenter(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void onViewIsReady() {
        try{
            getView().showPatients(db.patientDao().getAll());
        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }

    }
}
