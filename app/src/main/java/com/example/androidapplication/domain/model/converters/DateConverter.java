package com.example.androidapplication.domain.model.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public long fromDate(Date value) {
        return value.getTime();
    }

    @TypeConverter
    public Date toDate(long value) {
        return new Date(value);
    }
}
