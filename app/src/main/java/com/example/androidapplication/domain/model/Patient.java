package com.example.androidapplication.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Patient implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NotNull private String name;
    @NotNull private String surname;
    private String patronum;
    @NotNull @ColumnInfo(name = "birthday") private Date birthDay;


    public Patient(Long id,
                   @NotNull String name,
                   @NotNull String surname,
                   String patronum,
                   @NotNull Date birthDay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronum = patronum;
        this.birthDay = birthDay;
    }

    @Override
    public @NotNull String toString() {
        return getCardNumber()+"  "+this.getSurname()+" "+this.getName().subSequence(0,1)+"."+this.getPatronumic().subSequence(0,1)+".";
    }

    public String getFullName(){
        return this.getSurname()+" "+this.getName()+" "+this.getPatronumic();
    }

    public String getDetails() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return
                "birthDay:  " + format.format(birthDay) + "\n" +
                        " cardNumber:  " + getCardNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return Objects.equals(patient.id, this.id);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + (patronum != null ? patronum.hashCode() : 0);
        result = 31 * result + birthDay.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getSurname() {
        return surname;
    }

    public String getPatronumic() {
        return patronum;
    }

    public @NotNull  Date getBirthDay() {
        return birthDay;
    }

    public String getCardNumber() {
        return  String.format("%08d", id);
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    public void setPatronum(String patronum) {
        this.patronum = patronum;
    }

    public void setBirthDay(@NotNull Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPatronum() {
        return patronum;
    }
}
