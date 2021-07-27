package com.aldegad.capacitor.geolocation.connect;

import android.util.Log;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static String TAG = "aldegad.geolocation.connect.api";
    public static String url = "http://3.35.5.135/KUNYOUNG/managementservice.asmx/"; // default URL
    private static APIInterface apiInterface = null;
    private static void init() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        apiInterface = retrofit.create(APIInterface.class);
    }
    public static void run(String url, HashMap<String, RequestBody> params, APIResponseCallback responseCallback) {
        Log.d(TAG, "run.onResponse: " + "init");
        if(apiInterface == null) init();
        Log.d(TAG, "run.onResponse: " + "init:after");
        apiInterface.run(url, params).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                Log.d(TAG, "run.onResponse: " + response.body().code + " / " + response.body().message + " / " + response.body().data);
                responseCallback.run(response.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d(TAG, "run.onFailure: " + t.toString());
                call.cancel();
            }
        });
    }
}

