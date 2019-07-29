package com.example.androidapplication.presentation.contracts;

import com.example.androidapplication.domain.model.presenters.PatientWithSessions;
import com.example.androidapplication.presentation.presenters.IPresenter;
import com.example.androidapplication.presentation.views.IView;

import java.util.List;

public interface ViewPatientsContract {
    interface View extends IView{
        void showPatients(List<PatientWithSessions> patients);
    }

    interface Presenter extends IPresenter<View>{
        void onViewIsReady();
    }
}
