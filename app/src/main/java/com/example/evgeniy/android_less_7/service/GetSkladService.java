package com.example.evgeniy.android_less_7.service;

/**
 * Created by Evgeniy on 01.05.2017.
 */

import com.example.evgeniy.android_less_7.model.Sklad;
import com.example.evgeniy.android_less_7.model.SkladInfo;
import com.example.evgeniy.android_less_7.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetSkladService {
    @GET("sklad/")
    Call<List<Sklad>> getSkladDetails();

    @FormUrlEncoded
    @POST("sklad/getskladbyid/")
    Call<List<SkladInfo>> getSkladById(@Field("s_id") String s_id);

    @FormUrlEncoded
    @POST("sklad/setskladinfo/")
    Call<List<SkladInfo>> setSkladInfo(@Field("s_id") String s_id, @Field("s_name") String s_name, @Field("s_adress") String s_adress);

}
