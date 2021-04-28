package com.example.dai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

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