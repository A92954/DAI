package com.example.dai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dai.Backend.SessionManagement;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Profile extends AppCompatActivity {
    EditText nome, morada;
    Button save;
    String n, m;
    AsyncHttpClient client;
    RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nome = (EditText) findViewById(R.id.nomeID);
        morada = (EditText) findViewById(R.id.districtID);
        save = (Button) findViewById(R.id.saveProfileBtn);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement session = new SessionManagement(this);

        int id_child_int = session.getID_CHILD();
        String id_child = String.valueOf(id_child_int);

        client = new AsyncHttpClient();

        String URL = "http://93.108.170.117:8080/DAI-end/profile1?id_child=" + id_child;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = nome.getText().toString();
                m = morada.getText().toString();

                params = new RequestParams();
                params.put("child_name", n);
                params.put("address", m);

                client = new AsyncHttpClient();

                //Ir buscar a session

                //String URL = "http://93.108.170.117:8080/DAI-end/profile1?id_child=1";

                client.put(URL, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response.toString());
                        Toast.makeText(Profile.this, "Save Success" +response, Toast.LENGTH_SHORT).show();

                        //Fazer uma alteração para quando aquilo guardar os dados, nao aparecer nada no text field
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(Profile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                        //Aquilo altera na BD mas aparece a msg no tele a dizer Something Went Wrong
                    }
                });
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

        Button moreInfoBtn = (Button) findViewById(R.id.moreInfoBtn);
        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MoreInfoProf.class);
                startActivity(startIntent);
            }
        });
        //END OF BUTTON SECTION



    }
}