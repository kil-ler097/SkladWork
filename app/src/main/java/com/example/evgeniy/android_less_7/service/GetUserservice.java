package com.example.evgeniy.android_less_7.service;

/**
 * Created by Evgeniy on 27.04.2017.
 */

import com.example.evgeniy.android_less_7.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface GetUserservice {
    @GET("user/")
    Call<List<Users>> getUserDetails();

    @FormUrlEncoded
    @POST("user/authorize/")
    Call<Users> checkUserInfo(@Field("username") String username,@Field("password") String password);



}


