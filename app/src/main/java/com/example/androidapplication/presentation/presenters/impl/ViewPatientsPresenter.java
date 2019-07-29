package com.example.androidapplication.presentation.presenters.impl;

import com.example.androidapplication.domain.model.AppDatabase;
import com.example.androidapplication.domain.model.presenters.PatientWithSessions;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewPatientsPresenter
        extends BasePresenter<ViewPatientsContract.View>
        implements ViewPatientsContract.Presenter {

    private AppDatabase db;

    public ViewPatientsPresenter(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void onViewIsReady() {
        db.patientDao()
                .getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<PatientWithSessions>>() {
                    @Override
                    public void onSuccess(List<PatientWithSessions> patients) {
                        if(patients.isEmpty())
                            getView().handleError("No Items");
                        else
                            getView().showPatients(patients);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().handleError(e.getMessage());
                    }
        });
    }
}
