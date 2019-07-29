package com.example.androidapplication.presentation.presenters.impl;

import android.database.sqlite.SQLiteException;

import com.example.androidapplication.domain.model.AppDatabase;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.model.presenters.PatientWithSessions;
import com.example.androidapplication.presentation.contracts.EditAddContract;
import com.example.androidapplication.presentation.presenters.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EditAddPatientPresenter
        extends BasePresenter<EditAddContract.View>
        implements EditAddContract.Presenter {

    private AppDatabase db;

    public EditAddPatientPresenter(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void onSubmit(Patient patient) {
        try {
            if (patient.getId() == null)
                db.patientDao().insert(patient);
            else
                db.patientDao().update(patient);
            getView().handleSuccess();
        }
        catch(SQLiteException e){
            getView().handleError("Database unavailable. Try later");
        }
    }

    @Override
    public void onViewIsReady(long id) {
            db.patientDao().getById(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<PatientWithSessions>() {
                        @Override
                        public void onSuccess(PatientWithSessions patient) {
                            getView().showPatient(patient);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().handleError(e.getMessage());//"Patient with id " + id + "isn't founded"
                        }
                    });
    }
}
