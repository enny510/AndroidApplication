package com.example.androidapplication.domain.model.presenters;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.androidapplication.domain.model.Patient;
import com.example.androidapplication.domain.model.Session;

import java.util.List;

public class PatientWithSessions {
    @Embedded
    public Patient patient;

    @Relation(parentColumn = "id", entityColumn = "patient_id", entity = Session.class)
    public List<Session> sessions;
}
