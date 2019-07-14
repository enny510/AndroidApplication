package com.example.androidapplication.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.*;
import com.example.androidapplication.AndroidApplication;
import com.example.androidapplication.R;
import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.ui.utils.PatientListAdapter;

import javax.inject.Inject;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ViewPatientsActivity extends AppCompatActivity implements ViewPatientsContract.View {

    @Inject
    ViewPatientsContract.Presenter presenter;

    @BindView(R.id.task2List)
    ListView productsViewer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patients);
        AndroidApplication.getComponent().injects(this);
        ButterKnife.bind(this);

        presenter.attachView(this);
        presenter.onViewIsReady();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void fillList(List<Patient> patients){

                ListAdapter listAdapter = new PatientListAdapter(this,
                        patients);

                productsViewer.setAdapter(listAdapter);
                productsViewer.requestFocus();
    }

    @Override
    public void showPatients(List<Patient> patients) {
        if(patients.isEmpty())
            Toast.makeText(this, "no match results", Toast.LENGTH_LONG).show();
        else
            fillList(patients);
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

    @OnItemClick(R.id.task2List)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(this, PatientDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("patientId", id);
            intent.putExtras(bundle);
            startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                   ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle(((PatientListAdapter.ViewHolder)v.getTag()).t)
//        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "Открыть");
//        menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, "Сохранить");
    }
}
