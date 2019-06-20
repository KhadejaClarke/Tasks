package com.khadejaclarke.tasks.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.SupportingActivity;

public class CreateTaskFragment extends Fragment {
    Activity activity;
    EditText fieldTaskName;
    EditText fieldTaskDescription;
    CheckBox checkboxTaskDone;
    Button btnCreate;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fieldTaskName = view.findViewById(R.id.fieldTaskName);
        fieldTaskDescription = view.findViewById(R.id.fieldTaskDescription);
        checkboxTaskDone = view.findViewById(R.id.checkboxTaskDone);


        btnCreate  = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> {
            String name = fieldTaskName.getText().toString();
            String description = fieldTaskDescription.getText().toString();
            boolean isChecked = checkboxTaskDone.isChecked();

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(activity,
                        "Please enter your details!", Toast.LENGTH_LONG)
                        .show();
            } else {
                Intent mIntent = new Intent(activity, SupportingActivity.class);
                mIntent.putExtra("method", "POST");
                mIntent.putExtra("name", name);
                mIntent.putExtra("description", description);
                mIntent.putExtra("isChecked", isChecked);
                startActivity(mIntent);
            }
        });
    }




}
