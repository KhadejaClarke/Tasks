package com.khadejaclarke.tasks.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.TaskFetchr;
import com.khadejaclarke.tasks.adapters.RecyclerViewAdapter;
import com.khadejaclarke.tasks.models.Task;

import java.util.ArrayList;

public class ReadTasksFragment extends Fragment {
    Activity activity;
    ArrayList<Task> tasks;
    RecyclerView rv_read;
    RecyclerViewAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tasks = new TaskFetchr().fetchTasks();

        rv_read = view.findViewById(R.id.recyclerView);
        rv_read.setLayoutManager(new LinearLayoutManager(activity));

        adapter = new RecyclerViewAdapter(activity, tasks);
        rv_read.setAdapter(adapter);
    }


}
