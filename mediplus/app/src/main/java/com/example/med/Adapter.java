package com.example.med;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    MyDoctor[] myDoctors;

    Adapter(Context context, MyDoctor[] myDoctors) {
        this.layoutInflater = LayoutInflater.from(context);
        this.myDoctors = myDoctors;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String names, profs, studys, exps;
        final Integer imgs;

        final MyDoctor myDoctorList = myDoctors[position];
        holder.txtNameDoc.setText(myDoctorList.getDocName());
        holder.txtProfessionDoc.setText(myDoctorList.getDocProfession());
        holder.txtStudyDoc.setText(myDoctorList.getDocStudy());
        holder.txtExperienceDoc.setText(myDoctorList.getDocExperience());

        holder.imgDoc.setImageResource(myDoctorList.getDocImage());

        names = myDoctorList.getDocName();
        profs = myDoctorList.getDocProfession();
        studys = myDoctorList.getDocStudy();
        exps = myDoctorList.getDocExperience();
        imgs = myDoctorList.getDocImage();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DoctorDetails.class);
                i.putExtra("name", names);
                i.putExtra("prof", profs);
                i.putExtra("study", studys);
                i.putExtra("exp", exps);
                i.putExtra("image", imgs);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoctors.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameDoc, txtProfessionDoc, txtStudyDoc, txtExperienceDoc;
        ImageView imgDoc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameDoc = itemView.findViewById(R.id.txtNameDoc);
            txtProfessionDoc = itemView.findViewById(R.id.txtProfessionDoc);
            txtStudyDoc = itemView.findViewById(R.id.txtStudyDoc);
            txtExperienceDoc = itemView.findViewById(R.id.txtExperienceDoc);
            imgDoc = itemView.findViewById(R.id.imgDoc);
        }
    }
}
