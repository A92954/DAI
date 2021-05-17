package com.example.dai.Backend;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface API {
    @Multipart
    // POST request to upload an image from storage
    @PUT("photo/?id_child=1&id_activity=2")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);
}
