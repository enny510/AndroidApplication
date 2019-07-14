package com.example.androidapplication.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.R;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.repository.impl.PatientRepositoryImpl;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientDetailsPresenter;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientsPresenter;
import com.example.androidapplication.ui.fragments.PatientFormFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PatientDetailActivity extends AppCompatActivity implements ViewPatientDetailContract.View {

    @Inject
    ViewPatientDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        AndroidApplication.getComponent().injects(this);

        long id = (long) getIntent().getExtras().get("patientId");

        presenter.attachView(this);
        presenter.onViewIsReady(id);
    }

    @Override
    public void showPatient(@NotNull Patient patient) {
        PatientFormFragment patientDetailFragment = (PatientFormFragment)
                getFragmentManager().findFragmentById(R.id.detail_frag);
    }

    @Override
    public void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("patientId", patient);
    }
}
