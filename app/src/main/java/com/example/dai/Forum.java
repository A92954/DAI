package com.example.dai;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Forum extends AppCompatActivity {

    private RecyclerView shareList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<ForumModel> forumList;
    private RecyclerView.Adapter adapter;
    private String url = "http://93.108.170.117:8080/DAI-end/share";
    Dialog loading;
    Dialog failure;
    ImageView im1;
    ImageLoader mImageLoader;
    ImageView mImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        //BEGIN FORUM
        shareList = findViewById(R.id.forumList);

        forumList = new ArrayList<>();
        adapter = new ForumAdapter(getApplicationContext(), forumList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(shareList.getContext(), linearLayoutManager.getOrientation());


        shareList.setHasFixedSize(true);
        shareList.setLayoutManager(linearLayoutManager);
        shareList.addItemDecoration(dividerItemDecoration);
        shareList.setAdapter(adapter);



        updateAndroidSecurityProvider();
        getForum();
        //FINISH FORUM

        //BUTTON SECTION
        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn2);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });


    }
    private void getForum() {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        //LOADING SCREEN
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_popup_acti);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loading.show();

        //ERROR SCREEN
        failure = new Dialog(this);
        failure.setContentView(R.layout.error_popup_acti);
        failure.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ForumModel forum = new ForumModel();
                        forum.setComentario(jsonObject.getString("activity_comment"));
                        forum.setUsername(jsonObject.getString("name_child"));
                        //String ImageUrl = "http://93.108.170.117:8080/DAI-end/Images/" + jsonObject.optString("photo");
                        String ImageUrl = "https://4.bp.blogspot.com/-Zvk3ewqjE8Y/XJ00OoVelgI/AAAAAAAAFy8/PJmn5PVczaIdjwSrtpiY-B9r6V8ym-nIwCLcBGAs/s1600/Fetch%2Bimage%2Bfrom%2Bserver%25281%2529.png";
                        forum.setImage_URL(ImageUrl);

                        forumList.add(forum);
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
                loading.dismiss();
                failure.show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    //NAO MEXER SENAO CODIGO VAI DAR BOOM
    private void updateAndroidSecurityProvider() { try { ProviderInstaller.installIfNeeded(this); } catch (Exception e) { e.getMessage(); } }
}