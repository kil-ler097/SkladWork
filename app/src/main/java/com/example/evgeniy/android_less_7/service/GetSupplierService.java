package com.example.evgeniy.android_less_7.service;

import com.example.evgeniy.android_less_7.model.SupplierData;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Evgeniy on 08.05.2017.
 */

public interface GetSupplierService
{
    @GET("supplier/")
    Call<List<SupplierData>> getSupplierDetails();

    @FormUrlEncoded
    @POST("supplier/getsupplierbyid/")
    Call<List<SupplierData>> getSupplierById(@Field("sup_id") String sup_id);

    @FormUrlEncoded
    @POST("supplier/deletesupplier/")
    Call<List<SupplierData>> deletesupplier(@Field("sup_id") String sup_id);

    @FormUrlEncoded
    @POST("supplier/setsupplierbyid/")
    Call<List<SupplierData>> setSupplierById(@Field("sup_id") String sup_id, @Field("sup_name") String name,@Field("sup_data") String data);
}
