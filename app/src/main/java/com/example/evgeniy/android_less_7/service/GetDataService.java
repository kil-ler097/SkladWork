package com.example.evgeniy.android_less_7.service;

/**
 * Created by Evgeniy on 01.05.2017.
 */
import com.example.evgeniy.android_less_7.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {
    @FormUrlEncoded
    @POST("data/getdatabysklad/")
    Call<List<Data>> getDataDetails(@Field("s_id") int s_id);
}
