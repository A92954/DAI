package com.example.dai;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ActiDesc extends AppCompatActivity {

    private TextView local;
    private TextView hora;
    private TextView institution;
    private TextView dia;
    private TextView name;
    private String url;
    Dialog loading;
    Dialog failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_desc);

        //url = "http://93.108.170.117:8080/DAI-end/activity?id=13";

        //GET ID FROM PREVIOUS PAGE
        //PUTS ID IN A TEXTVIEW WITH 0 SIZE
        if (getIntent().hasExtra("placeholder_key")){
            TextView tv = (TextView) findViewById(R.id.id_placeholder);
            String text = getIntent().getExtras().getString("placeholder_key");
            tv.setText(text);
            getInfo(text);
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


    private void getInfo(String text) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        url = "http://93.108.170.117:8080/DAI-end/activity?id=" + text;


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


                    name = (TextView) findViewById(R.id.activTitleID);
                    name.setText(jsonObject.getString("name"));


                    local = (TextView) findViewById(R.id.localID);
                    local.setText(jsonObject.optString("local"));


                    institution = (TextView) findViewById(R.id.institutionID);
                    institution.setText(jsonObject.getString("institution"));


                    dia = (TextView) findViewById(R.id.dayID);
                    dia.setText(jsonObject.getString("schedule"));


                    String duracao = jsonObject.optString("start") + " - " + jsonObject.optString("end");
                    hora = (TextView) findViewById(R.id.horaID);
                    hora.setText(duracao);


                    //String ImageUrl = "http://93.108.170.117:8080/DAI-end/Images/" + jsonObject.optString("photo");
                    String ImageURL = "https://4.bp.blogspot.com/-Zvk3ewqjE8Y/XJ00OoVelgI/AAAAAAAAFy8/PJmn5PVczaIdjwSrtpiY-B9r6V8ym-nIwCLcBGAs/s1600/Fetch%2Bimage%2Bfrom%2Bserver%25281%2529.png";
                    ImageView ImageUrl = (ImageView) findViewById(R.id.activImage);

                    Picasso.get()
                            .load(ImageURL)
                            .resize(300, 300)
                            .centerInside()
                            .into(ImageUrl);





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


    //public void getInfo(String id){
    //    url = "http://93.108.170.117:8080/DAI-end/activity?id=" + id;
    //    final ProgressDialog progressDialog = new ProgressDialog(this);
    //    //LOADING SCREEN
    //    loading = new Dialog(this);
    //    loading.setContentView(R.layout.loading_popup_acti);
    //    loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    //    loading.show();
//
    //    //ERROR SCREEN
    //    failure = new Dialog(this);
    //    failure.setContentView(R.layout.error_popup_acti);
    //    failure.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
    //    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {
    //        @Override
    //        public void onResponse(JSONObject response) {
    //            try {
    //                local = (TextView) findViewById(R.id.localID);
    //                local.setText(response.getString("local"));
    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            }
    //            try {
    //                institution = (TextView) findViewById(R.id.institutionID);
    //                institution.setText(response.getString("institution"));
    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            }
    //            try {
    //                dia = (TextView) findViewById(R.id.dayID);
    //                dia.setText(response.getString("schedule"));
    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            }
    //            String duracao = null;
    //            try {
    //                duracao = response.getString("start") + response.getString("end");
    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            }
    //            hora = (TextView) findViewById(R.id.horaID);
    //            hora.setText(duracao);
    //        }
//
    //        //adapter.notifyDataSetChanged();
//
    //    },new Response.ErrorListener(){
    //        @Override
    //        public void onErrorResponse(VolleyError error) {
    //            loading.dismiss();
    //            failure.show();
    //        }
    //    });
//
    //    loading.dismiss();
    //    RequestQueue requestQueue = Volley.newRequestQueue(this);
    //    requestQueue.add(jsonObjectRequest);
//
    //}

    
    //private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
    //    @Override
    //    protected Bitmap doInBackground(String... params) {
    //        try {
    //            URL url = new URL("http://xxx.xxx.xxx/image.jpg");
    //            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //            connection.setDoInput(true);
    //            connection.connect();
    //            InputStream input = connection.getInputStream();
    //            Bitmap myBitmap = BitmapFactory.decodeStream(input);
    //            return myBitmap;
    //        }catch (Exception e){
    //            Log.d("ok",e.getMessage());
    //        }
    //        return null;
    //    }
//
    //    @Override
    //    protected void onPostExecute(Bitmap result) {
    //        ImageView imageView = (ImageView) findViewById(R.id.activImage);
    //        imageView.setImageBitmap(result);
    //    }
    //}



}