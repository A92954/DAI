package com.example.dai;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    ListView activList;
    ArrayList<CalendarModel> calendarList;
    NestedScrollView calendarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



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

        //CALENDAR

       activList = (ListView) findViewById(R.id.activList);
        calendarLayout = (NestedScrollView) findViewById(R.id.calendarLayout);

        calendarList = new ArrayList<>();
        new fetchData().execute();
        calendarLayout.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                new fetchData().execute();
            }
        });
        //END CALENDAR

    }



    public class fetchData extends AsyncTask<String, String, String> {

        @Override
        public void onPreExecute() {
            super .onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            calendarList.clear();
            String result = null;
            try {
                URL url = new URL("http://93.108.170.117:8080/DAI-end/current");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        stringBuilder.append(temp);
                    }
                    result = stringBuilder.toString();
                }else  {
                    result = "error";
                }

            } catch (Exception  e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);

            try {
                //JSONObject object = new JSONObject(s);
                JSONArray array = new JSONArray();

                for (int i = 0; i < array.length(); i++) {

                    JSONObject jsonObject = array.getJSONObject(i);
                    String day = jsonObject.getString("day");
                    String acti_name = jsonObject.getString("name");

                    CalendarModel model = new CalendarModel();
                    model.setDay(day);
                    model.setActi_name(acti_name);
                    calendarList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            CalendarAdapter adapter = new CalendarAdapter(MainPage.this, calendarList);
            activList.setAdapter(adapter);

        }
    }
}