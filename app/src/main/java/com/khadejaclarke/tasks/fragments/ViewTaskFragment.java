package com.khadejaclarke.tasks.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.khadejaclarke.tasks.MainActivity;
import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.fragments.UpdateTaskFragment;
import com.khadejaclarke.tasks.models.Task;

public class ViewTaskFragment extends Fragment {
    Activity activity;
    TextView textviewTaskName, textviewTaskDescription;
    CheckBox checkboxTaskDone;
    Button text_btn_edit, text_btn_delete;
    Task task;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  Activity)
            activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
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
        textviewTaskName = view.findViewById(R.id.textviewTaskName);
        textviewTaskName.setText(task.getTitle());

        textviewTaskDescription = view.findViewById(R.id.textviewTaskDescription);
        textviewTaskDescription.setText(task.getDescription());

        checkboxTaskDone = view.findViewById(R.id.checkboxTaskDone);
        checkboxTaskDone.setChecked(task.getDone());

        text_btn_edit = view.findViewById(R.id.text_btn_edit);
        text_btn_edit.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", task.getID());
            bundle.putString("title", task.getTitle());
            bundle.putString("description", task.getDescription());
            bundle.putBoolean("done", task.getDone());

            Fragment fragment = new UpdateTaskFragment();
            String tag = "Update Task";
            fragment.setArguments(bundle);

            ((MainActivity) activity).switchFragment(fragment, tag);
        });

        text_btn_delete = view.findViewById(R.id.text_btn_delete);
        text_btn_delete.setOnClickListener(v -> new AlertDialog.Builder(activity)
                .setTitle("Deletion Confirmation")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {})
                .setNegativeButton(android.R.string.no, (dialog, which) -> {})
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());

    }
}
