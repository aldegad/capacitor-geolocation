package com.aldegad.capacitor.geolocation.connect;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface APIInterface {
    @Multipart
    @POST
    Call<APIResponse> run(@Url String url, @PartMap Map<String, RequestBody> params);
}
