package com.example.evgeniy.android_less_7.service;

import com.example.evgeniy.android_less_7.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Evgeniy on 09.05.2017.
 */

public interface GetCategoryService {
    @GET("category/")
    Call<List<Category>> getCategoryDetails();

    @FormUrlEncoded
    @POST("category/getcategorybyid/")
    Call<List<Category>> getCategoryById(@Field("c_id") String c_id);

    @FormUrlEncoded
    @POST("category/setcategorybyid/")
    Call<List<Category>> setCategory(@Field("c_id") String c_id, @Field("c_name") String c_name);


}
