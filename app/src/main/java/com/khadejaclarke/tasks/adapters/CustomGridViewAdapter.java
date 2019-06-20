package com.khadejaclarke.tasks.adapters;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khadejaclarke.tasks.R;
import com.khadejaclarke.tasks.models.Action;


public class CustomGridViewAdapter extends ArrayAdapter<Action> {
    private Context context;
    private ArrayList<Action> data;

    public CustomGridViewAdapter(Context context, ArrayList<Action> data) {
        super(context, R.layout.grid_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = view;

        if (item == null) {
            item = inflater.inflate(R.layout.grid_item, parent, false);
        }

        TextView actionName = item.findViewById(R.id.item_text);
        actionName.setText(this.data.get(position).getName());

        ImageView actionIcon  = item.findViewById(R.id.item_image);
        actionIcon.setImageResource(this.data.get(position).getIcon());

        return item;

    }

}