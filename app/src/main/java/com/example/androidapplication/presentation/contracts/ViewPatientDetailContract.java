package com.example.androidapplication.presentation.contracts;

import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.presentation.presenters.IPresenter;
import com.example.androidapplication.presentation.views.IView;
import org.jetbrains.annotations.NotNull;

public interface ViewPatientDetailContract {
    interface View extends IView{
        void showPatient(@NotNull Patient patient);
    }

    interface Presenter extends IPresenter<View>{
        void onViewIsReady(long id);
    }
}
