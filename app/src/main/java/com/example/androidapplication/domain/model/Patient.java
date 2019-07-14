package com.example.androidapplication.domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String patronum;
    private Date birthDay;
    private Long cardNumber;
    private String diagnosis;
    private Date enterDate;
    private Date outDate;
    private boolean isPaid;


    public Patient(long id,
                   String name,
                   String surname,
                   String patronum,
                   long birthDay,
                   long cardNumber,
                   String diagnosis,
                   long enterDate,
                   long outDate,
                   int isPaid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronum = patronum;
        this.birthDay = new Date(birthDay);
        this.cardNumber = cardNumber;
        this.diagnosis = diagnosis;
        this.enterDate = new Date(enterDate);
        if(outDate!=0)
            this.outDate = new Date(outDate);
        else this.outDate = null;
        this.isPaid = isPaid == 1;
    }

    @Override
    public String toString() {
        return this.cardNumber+"  "+this.getSurname()+" "+this.getName().subSequence(0,1)+"."+this.getPatronumic().subSequence(0,1)+".";
    }

    public String getFullName(){
        return this.getSurname()+" "+this.getName()+" "+this.getPatronumic();
    }

    public String getDetails() {
        SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy");
        return
                "birthDay:  " + format.format(birthDay) + "\n" +
                        " cardNumber:  " + cardNumber +"\n" +
                        " diagnosis:   "+ diagnosis+"\n" +
                        " date of entering:   "+ format.format(enterDate)+"\n" +
                        " date of discharge:   "+ (outDate!=null?format.format(outDate):"")+"\n" +
                        " state:   "+ (isPaid?"Paid":"Free");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (id != null ? !id.equals(patient.id) : patient.id != null) return false;
        if (cardNumber != null ? !cardNumber.equals(patient.cardNumber) : patient.cardNumber != null)
            return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronum != null ? patronum.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
        return result;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronumic() {
        return patronum;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronumic(String patronumic) {
        this.patronum = patronumic;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

}
