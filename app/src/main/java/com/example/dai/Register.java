package com.example.dai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    EditText email, username, password;
    Button submit;
    String e, u, p;
    RequestParams params;
    AsyncHttpClient client;
    String URL = "http://93.108.170.117:8080/DAI-end/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.mailID);
        username = (EditText) findViewById(R.id.userID);
        password = (EditText) findViewById(R.id.passID);
        submit = (Button) findViewById(R.id.registerBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = email.getText().toString();
                u = username.getText().toString();
                p = password.getText().toString();

                params = new RequestParams();
                params.put("k1", e);
                params.put("k2", u);
                params.put("k3", p);
                client = new AsyncHttpClient();
                client.post(URL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(Register.this, "Submit Success" +response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(Register.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent startIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(startIntent);
            }
        });
    }
}