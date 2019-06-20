package com.khadejaclarke.tasks.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khadejaclarke.tasks.MainActivity;
import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.fragments.ViewTaskFragment;
import com.khadejaclarke.tasks.models.Task;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Task> tasks;

    public RecyclerViewAdapter(Activity activity, ArrayList<Task> tasks) {
        this.activity = activity;
        this.tasks = tasks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView task_id;
        TextView task_title;
        TextView task_description;
        TextView task_done;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            task_id = view.findViewById(R.id.task_id);
            task_title = view.findViewById(R.id.task_title);
            task_description = view.findViewById(R.id.task_description);
            task_done = view.findViewById(R.id.task_done);

        }

        void bind(Task task) {
            task_id.setText(new StringBuilder().append("Task ").append(task.getID()));
            task_title.setText(new StringBuilder().append(":  ").append(task.getTitle()));
            task_description.setText(new StringBuilder().append("Description: ").append(task.getDescription()));
            task_done.setText(new StringBuilder().append("Done: ").append(task.getDone().toString()));
        }


        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id", task_id.getText().toString());
            bundle.putString("title", task_title.getText().toString());
            bundle.putString("description", task_description.getText().toString());
            bundle.putString("done", task_done.getText().toString());

            Fragment fragment = new ViewTaskFragment();
            String tag = "View Task";
            fragment.setArguments(bundle);

            ((MainActivity) activity).switchFragment(fragment, tag);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.rv_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
