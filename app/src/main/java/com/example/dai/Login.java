package com.example.dai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class Login extends AppCompatActivity {
    EditText username, password;
    String u, p;
    AsyncHttpClient client;
    RequestParams params;
    String URL = "http://93.108.170.117:8080/DAI-end/login";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registBtn = (Button) findViewById(R.id.registBtn);
        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(startIntent);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });

        /*



        username = (EditText)findViewById(R.id.usernameID);
        password = (EditText)findViewById(R.id.passwordD);
        login = (Button)findViewById(R.id.loginBtn);

        SessionManagement session = new SessionManagement(Login.this);
        int userID = session.getSession();

        if (userID != -1) {
            Intent intent = new Intent(Login.this, com.example.dai.MainPage.class);
            startActivity(intent);
            finish();
        } else {

        }

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                u = username.getText().toString();
                p = password.getText().toString();

                params = new RequestParams();
                params.put("username", u);
                params.put("password", p);
                client = new AsyncHttpClient();
                client.post(URL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        String email = "";
                        int id_user = 0, id_child = 0;

                        try {
                            email = response.getString("email");
                            id_user = response.getInt("id_user");
                            id_child = response.getInt("id_child");
                        } catch (JSONException exc) {
                            exc.printStackTrace();
                        }

                        User us = new User(email, id_user);
                        Children ch = new Children(id_child);
                        SessionManagement session = new SessionManagement(Login.this);
                        session.saveSession(us, ch);

                        Toast.makeText(Login.this, "Login Success" +response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(Login.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });*/
    }
}