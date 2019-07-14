package com.example.androidapplication.config;

import com.example.androidapplication.config.modules.AppModule;
import com.example.androidapplication.ui.activities.EditAddActivity;
import com.example.androidapplication.ui.activities.PatientDetailActivity;
import com.example.androidapplication.ui.activities.ViewPatientsActivity;
import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void injects(EditAddActivity mainActivity);
    void injects(PatientDetailActivity mainActivity);
    void injects(ViewPatientsActivity mainActivity);
}
