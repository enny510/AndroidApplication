package com.example.androidapplication.config.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.androidapplication.domain.model.AppDatabase;
import com.example.androidapplication.presentation.contracts.EditAddContract;
import com.example.androidapplication.presentation.contracts.ViewPatientDetailContract;
import com.example.androidapplication.presentation.contracts.ViewPatientsContract;
import com.example.androidapplication.presentation.presenters.impl.EditAddPatientPresenter;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientDetailsPresenter;
import com.example.androidapplication.presentation.presenters.impl.ViewPatientsPresenter;
import dagger.Module;
import dagger.Provides;

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
    ViewPatientDetailContract.Presenter provideViewPatientDetailsPresenter(AppDatabase db) {
        return new ViewPatientDetailsPresenter(db);
    }

    @Provides
    EditAddContract.Presenter provideEditAddPresenter(AppDatabase db) {
        return new EditAddPatientPresenter(db);
    }

    @Provides
    ViewPatientsContract.Presenter provideViewPatientsPresenter(AppDatabase db) {
        return new ViewPatientsPresenter(db);
    }

    @Provides
    AppDatabase provideDBInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "database").build();
    }
}
