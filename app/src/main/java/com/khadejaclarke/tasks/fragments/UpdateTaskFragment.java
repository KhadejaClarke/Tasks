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
import com.khadejaclarke.tasks.models.Task;

public class UpdateTaskFragment extends Fragment {
    Activity activity;
    Task task;
    EditText fieldTaskName;
    EditText fieldTaskDescription;
    CheckBox checkboxTaskDone;
    Button btnUpdate;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTask();
        initWidgets(view);
    }

    private void initTask() {
        int id = Integer.valueOf(getArguments().getString("id"));
        String title = getArguments().getString("title");
        String description = getArguments().getString("description");
        boolean done = Boolean.valueOf(getArguments().getString("done"));

        task = new Task(id, title, description, done);
    }

    private void initWidgets(View view) {
        fieldTaskName = view.findViewById(R.id.fieldTaskName);
        fieldTaskName.setText(task.getTitle());

        fieldTaskDescription = view.findViewById(R.id.fieldTaskDescription);
        fieldTaskDescription.setText(task.getDescription());

        checkboxTaskDone = view.findViewById(R.id.checkboxTaskDone);
        checkboxTaskDone.setChecked(task.getDone());

        btnUpdate = view.findViewById(R.id.btnCreate);
        btnUpdate.setOnClickListener(v -> {
            String name = fieldTaskName.getText().toString();
            String description = fieldTaskDescription.getText().toString();
            boolean isChecked = checkboxTaskDone.isChecked();

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(activity, "Blank fields are not allowed.", Toast.LENGTH_LONG)
                        .show();
            } else {
                Intent mIntent = new Intent(activity, SupportingActivity.class);
                mIntent.putExtra("method", "PUT");
                mIntent.putExtra("id", task.getID());
                mIntent.putExtra("name", name);
                mIntent.putExtra("description", description);
                mIntent.putExtra("isChecked", isChecked);
                startActivity(mIntent);
            }
        });
    }
}
