package com.example.androidapplication.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidapplication.R;
import com.example.androidapplication.domain.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientFormFragment extends Fragment {

    private Calendar dateAndTime = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    DatePickerDialog.OnDateSetListener d = (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    };

    DatePickerDialog.OnDateSetListener dd = (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_patient_form, container, false);

        EditText cardNumberField = layout.findViewById(R.id.cardNumberTextView);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(8);
        cardNumberField.setFilters(FilterArray);

        EditText enterDateField = layout.findViewById(R.id.enterDateTextView);
        enterDateField.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus) {
                new DatePickerDialog(getActivity(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
                view.clearFocus();
            }
        });
        EditText outDateField = layout.findViewById(R.id.outDateTextView);
        outDateField.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus) {
                new DatePickerDialog(getActivity(), dd,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
                view.clearFocus();
            }
        });

        return layout;
    }
}
