package com.khadejaclarke.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import volley.Config_URL;

public class SupportingActivity extends Activity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporting);

        Intent intent = getIntent();

        switch (intent.getStringExtra("method")) {
            case "POST" : {
                createTask(intent.getStringExtra("name"),
                        intent.getStringExtra("description"),
                        intent.getBooleanExtra("isChecked", false));
                break;

            }

            case "GET" : {
                getTasks();
                break;
            }

            case "PUT" : {
                //updateTask();
                break;
            }

            case "DELETE" : {
                //deleteTask();
                break;
            }
        }

    }

    private void createTask(final String title, final String description, final boolean isChecked) {
/*
        Task task = new Task(title, description, isChecked);

        Gson gson = new Gson();

        System.out.println(gson.toJson(task));
*/

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config_URL.URL_CREATE,
            new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to create url
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("description", description);
                params.put("done", String.valueOf(isChecked));

                return params;
            }

        };

        queue.add(stringRequest);

    }

    private void getTasks() {
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config_URL.URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
