package com.example.dai;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.security.ProviderInstaller;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class History extends AppCompatActivity {
    private RecyclerView activityList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<HistoryModel> historyList;
    private RecyclerView.Adapter adapter;
    private String url = "http://93.108.170.117:8080/DAI-end/child_activity?id_child=1";
    Dialog myDialog;
    Dialog loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        //INICIO HISTORICO
        activityList = findViewById(R.id.historyList);

        historyList = new ArrayList<>();
        adapter = new HistoryAdapter(getApplicationContext(), historyList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(activityList.getContext(), linearLayoutManager.getOrientation());

        activityList.setHasFixedSize(true);
        activityList.setLayoutManager(linearLayoutManager);
        activityList.addItemDecoration(dividerItemDecoration);
        activityList.setAdapter(adapter);

        updateAndroidSecurityProvider();
        getHistory();
        //FIM HISTORICO



        activityList.addOnItemTouchListener(new RecyclerItemClickListener(this, activityList ,new RecyclerItemClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                showPop();

            }

            @Override
            public void onLongItemClick(View view, int position) {
                showPop();

            }
        }));



        myDialog = new Dialog(this);

        ImageButton returnBtn = (ImageButton) findViewById(R.id.return5Btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });
    }

    //FETCH DO HISTORICO
    public void getHistory(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_popup_acti);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        HistoryModel history = new HistoryModel();

                        history.setData(jsonObject.getString("schedule"));
                        history.setActi_name(jsonObject.getString("name"));
                        history.setLocal(jsonObject.getString("address"));
                        String ImageUrl = "http://93.108.170.117:8080/DAI-end/Images/" + jsonObject.optString("file");
                        //String ImageUrl = "https://pbs.twimg.com/profile_images/888907252702347265/g2JwwLDR_400x400.jpg";
                        //String ImageUrl = "https://scontent.fopo4-2.fna.fbcdn.net/v/t1.18169-9/11924268_878778882158348_4837939208486181417_n.jpg?_nc_cat=100&ccb=1-3&_nc_sid=174925&_nc_eui2=AeFHyU6IQdrx6TQvylsPWpTUyOPuuww3QbfI4-67DDdBt0eokzLeoEDPCtJa82GLgg2Zgcq_ntpc_xq0BRzwubrH&_nc_ohc=9BIIu_axbPUAX8botKT&_nc_ht=scontent.fopo4-2.fna&oh=822a51f9e3e6eb2a37dcccfbf8dee8ec&oe=60C10DAD";
                        history.setImageURL(ImageUrl);

                        historyList.add(history);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        loading.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
               loading.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    //FIM DO FETCH

    //FUNÇAO DO POP-UP
    public void showPop(){
        myDialog.setContentView(R.layout.popup_acti);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        //GUARDAR ALTERAÇÕES DO POPUP
       Button save = (Button) myDialog.findViewById(R.id.saveShareBtn);
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setInfo();
               myDialog.dismiss();
           }
       });

       //SAIR DO POP-UP
        ImageView leavePopbtn = (ImageView) myDialog.findViewById(R.id.leavePopbtn);
        leavePopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showPopup(View v){
        myDialog.setContentView(R.layout.popup_acti);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        ImageView leavePopbtn = (ImageView) myDialog.findViewById(R.id.leavePopbtn);
        leavePopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

    }


    //FETCH DO POPUP
    public void setInfo() {

        RatingBar bar;
        EditText comment;

        String c;
        AsyncHttpClient client;
        RequestParams params;
        double rating = 0;

        bar = (RatingBar) myDialog.findViewById(R.id.ratingBar);
        int numStars = (int) bar.getRating();



        comment = (EditText) myDialog.findViewById(R.id.getCommentId);

        c = comment.getText().toString();

        params = new RequestParams();

        params.put("activity_comment", c);
        params.put("activity_evaluation", numStars);

        client = new AsyncHttpClient();

        //Ir buscar a session

        String URL1 = "http://93.108.170.117:8080/DAI-end/evaluation?id_child=1&id_activity=3";

        client.post(URL1, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());

                //Fazer uma alteração para quando aquilo guardar os dados, nao aparecer nada no text field
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                //Aquilo altera na BD mas aparece a msg no tele a dizer Something Went Wrong
            }
        });
    }


    private void updateAndroidSecurityProvider() { try { ProviderInstaller.installIfNeeded(this); } catch (Exception e) { e.getMessage(); }}

}