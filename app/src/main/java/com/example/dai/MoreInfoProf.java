package com.example.dai;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dai.Backend.SessionManagement;
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
    private String url;
    Dialog failure;
    Dialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement session = new SessionManagement(this);

        int id_child_int = session.getID_CHILD();
        String child_id = String.valueOf(id_child_int);

        getProfileMoreInfo(child_id);

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

                String URL1 = "http://93.108.170.117:8080/DAI-end/profile2?id_child=" + child_id;

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

                String URL2 = "http://93.108.170.117:8080/DAI-end/profile3?id_child=" + child_id;

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

    private void getProfileMoreInfo(String text) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        url = "http://93.108.170.117:8080/DAI-end/child?id=1";

        //LOADING SCREEN
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_popup_acti);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();

        //ERROR SCREEN
        failure = new Dialog(this);
        failure.setContentView(R.layout.error_popup_acti);
        failure.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try{
                    EditText idade = (EditText) findViewById(R.id.ageID);
                    idade.setText(jsonObject.optString("child_age"));

                    TextView profissao = (TextView) findViewById(R.id.profissionID);
                    profissao.setText(jsonObject.optString("future_profession"));

                    TextView pai1 = (TextView) findViewById(R.id.firstParentID);
                    pai1.setText(jsonObject.optString("parent1_name"));

                    TextView pai2 = (TextView) findViewById(R.id.secondParentID);
                    pai2.setText(jsonObject.optString("parent2_name"));

                    TextView contacto = (TextView) findViewById(R.id.editTextPhone);
                    contacto.setText(jsonObject.optString("contact"));


                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                failure.show();
            }
        });

        loading.dismiss();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jr);
    }

}