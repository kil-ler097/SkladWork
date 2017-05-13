package com.example.evgeniy.android_less_7.service;

/**
 * Created by Evgeniy on 01.05.2017.
 */
import android.text.Editable;

import com.example.evgeniy.android_less_7.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataService {
    @FormUrlEncoded
    @POST("data/getdatabysklad/")
    Call<List<Data>> getDataDetails(@Field("s_id") String s_id);

    @FormUrlEncoded
    @POST("data/getdatabyid/")
    Call<List<Data>> getDataById(@Field("data_id") String data_id);

    @FormUrlEncoded
    @POST("data/setdatabyid/")
    Call<List<Data>> saveGood(@Field("data_id") String data_id,
                              @Field("t1") String t1,
                              @Field("t2") String t2,
                              @Field("t3") String t3,
                              @Field("c11") String c11,
                              @Field("s_id") String s_id,
                              @Field("selected_sup") String selected_sup);

}
