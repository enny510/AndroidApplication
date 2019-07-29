package com.example.androidapplication.ui.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidapplication.domain.model.presenters.PatientWithSessions;
import com.example.androidapplication.ui.activities.EditAddActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class PatientListAdapter extends ArrayAdapter<PatientWithSessions> {

    private final Context context;
    private final List<PatientWithSessions> values;

    public PatientListAdapter(Context context, List<PatientWithSessions> values) {
        super(context, android.R.layout.simple_list_item_2, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public @NonNull View getView(int position, View convertView, @NotNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);

            holder = ViewHolder.createViewHolderFromViewAndWithPatient(convertView, values.get(position));
            convertView.setTag(holder);
            convertView.setOnClickListener((v) -> toggleItem(v));
            convertView.setOnLongClickListener((v) -> moveToEditForm(v));
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(holder.patient.patient.toString());
        holder.text1.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        holder.text2.setText(holder.patient.patient.getDetails() + "\n" + holder.patient.sessions.get(0));
        holder.text2.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
        holder.text2.setVisibility(View.GONE);

        return convertView;
    }

    private void toggleItem(View v){
        ViewHolder viewHolder = ((ViewHolder)v.getTag());
        viewHolder.collapsed = !viewHolder.collapsed;
        viewHolder.text2.setVisibility(viewHolder.collapsed? View.GONE: View.VISIBLE);
    }

    private boolean moveToEditForm(View v){
        ViewHolder viewHolder = ((ViewHolder)v.getTag());
        Intent intent = new Intent(context, EditAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("patientId", viewHolder.patient.patient.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
        return true;
    }

    static class ViewHolder {
        boolean collapsed = true;
        PatientWithSessions patient;
        TextView text1;
        TextView text2;

        static ViewHolder createViewHolderFromViewAndWithPatient(View v, PatientWithSessions p){
            ViewHolder holder = new ViewHolder();
            holder.patient = p;
            holder.text1 = v.findViewById(android.R.id.text1);
            holder.text1.setText(holder.patient.toString());
            holder.text2 = v.findViewById(android.R.id.text2);
            return holder;
        }
    }
}
