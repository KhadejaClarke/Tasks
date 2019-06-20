package com.khadejaclarke.tasks.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khadejaclarke.tasks.MainActivity;
import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.adapters.CustomGridViewAdapter;
import com.khadejaclarke.tasks.models.Action;

public class MainFragment extends Fragment {
    Activity activity;
    GridView gridView;
    ArrayList<Action> Actions = new ArrayList<>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        Actions.add(new Action("Create", R.drawable.create));
        Actions.add(new Action("Read", R.drawable.read));
        Actions.add(new Action("Update", R.drawable.update));
        Actions.add(new Action("Delete", R.drawable.delete));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridView1);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Fragment fragment = null;
                String tag = "";

                switch (position) {
                    case 0: {
                        fragment = new CreateTaskFragment();
                        tag = "Create Task";
                        break;
                    }

                    case 1: {
                        fragment = new ReadTasksFragment();
                        tag = "Read Tasks";
                        break;
                    }

                    case 2: {
                        fragment = new UpdateTaskFragment();
                        tag = "Update Task";
                        break;
                    }

                    case 3: {
                        fragment = new ViewTaskFragment();
                        tag = "Delete Task";
                        break;
                    }
                }

                ((MainActivity) activity).switchFragment(fragment, tag);
            }
        });

        customGridAdapter = new CustomGridViewAdapter(view.getContext(), Actions);
        gridView.setAdapter(customGridAdapter);

    }

}
