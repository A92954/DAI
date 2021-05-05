package com.example.dai;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActiDesc extends AppCompatActivity {

    private TextView local;
    private TextView hora;
    private TextView institution;
    private TextView dia;
    private String url;
    Dialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_desc);

        if (getIntent().hasExtra("placeholder_key")){
            TextView tv = (TextView) findViewById(R.id.id_placeholder);
            String text = getIntent().getExtras().getString("placeholder_key");
            tv.setText(text);
            getInfo(tv.getText().toString());
        }

        //BUTTON SECTION
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn3);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });

    }

    public void getInfo(String id){
        url = "http://93.108.170.117:8080/DAI-end/activity?id=" + id;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_popup_acti);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    local = (TextView) findViewById(R.id.localID);
                    local.setText(response.getString("local"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    institution = (TextView) findViewById(R.id.institutionID);
                    institution.setText(response.getString("institution"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    dia = (TextView) findViewById(R.id.dayID);
                    dia.setText(response.getString("schedule"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String duracao = null;
                try {
                    duracao = response.getString("start") + response.getString("end");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hora = (TextView) findViewById(R.id.horaID);
                hora.setText(duracao);
            }


            /*adapter.notifyDataSetChanged();*/


        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

}