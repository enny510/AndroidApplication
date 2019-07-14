package com.example.androidapplication.config.modules;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.androidapplication.domain.repository.PatientRepository;
import com.example.androidapplication.domain.repository.impl.PatientRepositoryImpl;
import com.example.androidapplication.presentation.contracts.EditAddContract;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.impl.EditAddPatientPresenter;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientDetailsPresenter;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientsPresenter;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    //@Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    ViewPatientDetailContract.Presenter provideViewPatientDetailsPresenter(PatientRepository repository) {
        return new ViewPatientDetailsPresenter(repository);
    }

    @Provides
    EditAddContract.Presenter provideEditAddPresenter(PatientRepository repository) {
        return new EditAddPatientPresenter(repository);
    }

    @Provides
    ViewPatientsContract.Presenter provideViewPatientsPresenter(PatientRepository repository) {
        return new ViewPatientsPresenter(repository);
    }

    @Provides
    PatientRepository providePatientRepository(Context context) {
        return new PatientRepositoryImpl(context);
    }
}
