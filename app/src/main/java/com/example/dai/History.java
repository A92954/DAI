package com.example.dai;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dai.Backend.API;
import com.example.dai.Backend.RetrofitClient;
import com.example.dai.Backend.SessionManagement;
import com.google.android.gms.security.ProviderInstaller;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class History extends AppCompatActivity {
    private RecyclerView activityList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<HistoryModel> historyList;
    private RecyclerView.Adapter adapter;
    //private String url = "http://93.108.170.117:8080/DAI-end/child_activity?id_child=1";
    Dialog myDialog;
    Dialog loading;

    Button upload;

    private static final int PICK_IMAGE_REQUEST = 9544;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    SessionManagement session = new SessionManagement(this);

    int id_child_int = session.getID_CHILD();
    String id_child = String.valueOf(id_child_int);

    String url = "http://93.108.170.117:8080/DAI-end/child_activity?" +id_child;

    String part_image;
    Uri selectedImage;

    //private static final int STORAGE_PERMISSION_CODE = 1111;
    //private static final int PICK_IMAGE_REQUEST = 11;


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


        //requestStoragePermission();



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


        //upload = (Button) myDialog.findViewById(R.id.uploadBtn);

        /*upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePhoto();
                pick();
                uploadImage();
            }
        });*/
    }

    /*private void requestStoragePermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }*/

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


    public void updatePhoto() {
        startActivity(new Intent(History.this, History.class));
    }

    public void pick() {
        verifyStoragePermissions(History.this);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }

    // Method to get the absolute path of the selected image from its URI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedImage = data.getData();                                                         // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, imageProjection, null, null, null);
                if(cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    // Get the image file absolute path
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Upload the image to the remote database
    public void uploadImage() {
        File imageFile = new File(part_image);                                                          // Create a file using the absolute path of the image
        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), reqBody);
        API api = RetrofitClient.getInstance().getAPI();
        Call<ResponseBody> upload = api.uploadImage(partImage);
        upload.enqueue(new Callback<ResponseBody>() {

            protected void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccess()) {
                    Toast.makeText(History.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(History.this, History.class);
                    main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
                }
            }

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(History.this, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void verifyStoragePermissions(History activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void updateAndroidSecurityProvider() { try { ProviderInstaller.installIfNeeded(this); } catch (Exception e) { e.getMessage(); }}

}