package com.example.androidapplication.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(
            entity = Patient.class,
            parentColumns = "id",
            childColumns = "patient_id")
)
public class Session {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NotNull private String diagnosis;
    @NotNull @ColumnInfo(name = "enter_date") private Date enterDate;
    @ColumnInfo(name = "out_date") private Date outDate;
    @ColumnInfo(name = "is_paid") private boolean isPaid;
    @NotNull @ColumnInfo(name = "patient_id") private Long patientId;

    public Session(Long id,
                   @NotNull String diagnosis,
                   @NotNull Date enterDate,
                   Date outDate,
                   Boolean isPaid,
                   @NotNull Long patientId) {
        this.id = id;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.enterDate = enterDate;
        this.outDate = outDate;
        this.isPaid = isPaid;
    }

    public @NotNull Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(@NotNull Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public @NotNull String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(@NotNull String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotNull Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "diagnosis: '" + diagnosis + '\'' +
                "\n enterDate: " + format.format(enterDate) +
                "\n outDate: " + format.format(outDate);
    }
}
