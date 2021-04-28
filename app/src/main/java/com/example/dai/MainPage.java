package com.example.dai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private RecyclerView activList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<CalendarModel> calendarList;
    private RecyclerView.Adapter adapter;
    private String url = "http://93.108.170.117:8080/DAI-end/current";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //BEGIN CALENDAR
        activList = findViewById(R.id.activList);

        calendarList = new ArrayList<>();
        adapter = new CalendarAdapter(getApplicationContext(), calendarList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(activList.getContext(), linearLayoutManager.getOrientation());


        activList.setHasFixedSize(true);
        activList.setLayoutManager(linearLayoutManager);
        activList.addItemDecoration(dividerItemDecoration);
        activList.setAdapter(adapter);

        updateAndroidSecurityProvider();
        getCalendar();
        //FINISH CALENDAR


        //BUTTON SECTION
        Button forumBtn = (Button) findViewById(R.id.forumBtn);
        forumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Forum.class);
                startActivity(startIntent);
            }
        });

        Button suggestion = (Button) findViewById(R.id.suggestionBtn);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Suggestion.class);
                startActivity(startIntent);
            }
        });

        Button historyBtn = (Button) findViewById(R.id.historyBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), History.class);
                startActivity(startIntent);
            }
        });

        Button profileBtn = (Button) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
            }
        });
        //END OF BUTTON SECTION

    }


    //FETCH DO CALENDARIO
    private void getCalendar() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        CalendarModel calendar = new CalendarModel();
                        calendar.setDay(jsonObject.getString("day"));
                        calendar.setActi_name(jsonObject.getString("name"));

                        calendarList.add(calendar);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    //NAO SEI O QUE FAZ MAS FAZ O CODIGO FAZER BLEEP BLOOP BLEEP
    //NAO RETIRAR
    private void updateAndroidSecurityProvider() { try { ProviderInstaller.installIfNeeded(this); } catch (Exception e) { e.getMessage(); } }
}


