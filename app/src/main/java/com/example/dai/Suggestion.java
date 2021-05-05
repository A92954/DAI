package com.example.dai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Suggestion extends AppCompatActivity {

    EditText comment;
    Button submit;
    String c;
    RequestParams params;
    AsyncHttpClient client;
    String URL = "http://93.108.170.117:8080/DAI-end/suggestion?id_child=4";
    private static final String TAG = "Suggestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        comment = (EditText)findViewById(R.id.textBoxSuggestionID);
        submit = (Button)findViewById(R.id.saveSendBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = comment.getText().toString();

                params = new RequestParams();
                params.put("comment", c);
                client = new AsyncHttpClient();

                client.post(URL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response.toString());
                        Toast.makeText(Suggestion.this, "Submit Success" +response, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(Suggestion.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });

        //BUTTON SECTION
        ImageButton returnBtn = (ImageButton) findViewById(R.id.return3Btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });
    }
}