package com.example.androidapplication.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.R;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.presentation.contracts.EditAddContract;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.ui.fragments.PatientFormFragment;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EditAddActivity extends AppCompatActivity implements EditAddContract.View {

    @Inject
    EditAddContract.Presenter presenter;

    private Patient patient;
    private Calendar dateAndTime=Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @BindView(R.id.idTextView)
    TextView idField;

    @BindView(R.id.nameTextView)
    EditText nameField;

    @BindView(R.id.surnameTextView)
    EditText surnameField;

    @BindView(R.id.patronumicTextView)
    EditText patronumicField;

    @BindView(R.id.birthDayTextView)
    EditText birthDayField;

    @BindView(R.id.diagnosisTextView)
    EditText diagnosisField;

    @BindView(R.id.enterDateTextView)
    EditText enterDateField;

    @BindView(R.id.isPaidCheckBox)
    CheckBox isPaidCB;

    @BindView(R.id.outDateTextView)
    EditText outDateField;

    @BindView(R.id.cardNumberTextView)
    EditText cardNumberField;

    DatePickerDialog.OnDateSetListener d = (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateToField(findViewById(R.id.enterDateTextView));
        };

    DatePickerDialog.OnDateSetListener dd = (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateToField(findViewById(R.id.outDateTextView));
    };

    public void setDateToField(View view){
        ((EditText)view).setText(dateAndTime.get(Calendar.YEAR) + "-" + (dateAndTime.get(Calendar.MONTH)+1) + "-" + dateAndTime.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add);
        AndroidApplication.getComponent().injects(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            patient = null;
        }
        else{
            presenter.onViewIsReady(bundle.getLong("patientId"));
        }
    }

    @Override
    public void showPatient(@NotNull Patient patient) {
        this.patient = patient;

        idField.setText(String.valueOf(patient.getId()));
        nameField.setText(String.valueOf(patient.getName()));
        surnameField.setText(String.valueOf(patient.getSurname()));
        patronumicField.setText(String.valueOf(patient.getPatronumic()));
        birthDayField.setText(String.valueOf(patient.getBirthDay()));
        cardNumberField.setText(String.valueOf(patient.getCardNumber()));
        diagnosisField.setText(String.valueOf(patient.getDiagnosis()));
        enterDateField.setText(format.format(patient.getEnterDate()));
        isPaidCB.setChecked(patient.isPaid());

        if(patient.getOutDate()!=null)
            outDateField.setText(format.format(patient.getOutDate()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.suggest_action:
                submit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submit() {

        Long cardNumberFieldValue;

        try {
            cardNumberFieldValue = Long.valueOf(cardNumberField.getText().toString());
        } catch (NumberFormatException exp) {
            //Toast.makeText(this,R.string.card_number_field_error,Toast.LENGTH_LONG).show();
            //cardNumberField.setError(getResources().getString(R.string.card_number_field_error));
// Задаем динамически цвет текста подсказки в EditText
            cardNumberField.setHintTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            return;
        }
        String diagnosisFieldText = ((EditText) findViewById(R.id.diagnosisTextView)).getText().toString();
        if (diagnosisFieldText.equals("")) diagnosisFieldText = "не определен";
    }

    @Override
    public void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleSuccess() {
        Intent intent=new Intent(this,ViewPatientsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("patientId", patient.getId());
    }
}
