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

public class MoreInfoProf extends AppCompatActivity {
    EditText idade, profissao, pai1, pai2;
    Button save1, save2;
    String i, p, p1, p2;
    AsyncHttpClient client;
    RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_prof);
        idade = (EditText) findViewById(R.id.ageID);
        profissao = (EditText) findViewById(R.id.profissionID);
        pai1 = (EditText) findViewById(R.id.firstParentID);
        pai2 = (EditText) findViewById(R.id.secondParentID);

        save1 = (Button) findViewById(R.id.saveMoreInfoBtn1);
        save2 = (Button) findViewById(R.id.saveMoreInfoBtn2);

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = idade.getText().toString();
                p = profissao.getText().toString();

                params = new RequestParams();
                params.put("age", i);
                params.put("profession", p);

                client = new AsyncHttpClient();

                String URL1 = "http://93.108.170.117:8080/DAI-end/profile2?id_child=1";

                client.put(URL1, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response.toString());
                        Toast.makeText(MoreInfoProf.this, "Save Success" +response, Toast.LENGTH_SHORT).show();

                        //Fazer uma alteração para quando aquilo guardar os dados, nao aparecer nada no text field
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(MoreInfoProf.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                        //Aquilo altera na BD mas aparece a msg no tele a dizer Something Went Wrong
                    }
                });
            }
        });

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1 = pai1.getText().toString();
                p2 = pai2.getText().toString();

                params = new RequestParams();
                params.put("parent1_name", p1);
                params.put("parent2_name", p2);

                client = new AsyncHttpClient();

                String URL2 = "http://93.108.170.117:8080/DAI-end/profile3?id_child=1";

                client.put(URL2, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response.toString());
                        Toast.makeText(MoreInfoProf.this, "Save Success" +response, Toast.LENGTH_SHORT).show();

                        //Fazer uma alteração para quando aquilo guardar os dados, nao aparecer nada no text field
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(MoreInfoProf.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

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
                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
            }
        });
    }
}