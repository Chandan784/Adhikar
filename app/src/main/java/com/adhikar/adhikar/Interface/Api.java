package com.adhikar.adhikar.Interface;

import com.adhikar.adhikar.Modal.LoginResponce;
import com.adhikar.adhikar.Modal.SubDetailModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
@FormUrlEncoded

    @POST("login.php")
    Call<LoginResponce> logIn(@Field("phone") String phone, @Field("password") String password );

    @FormUrlEncoded
    @POST("signup.php")
    Call<LoginResponce> signUp(@Field("name") String name, @Field("phone") String phone,@Field("age") String age,@Field("password") String password,@Field("date") String date );



    @GET("getdata.php")
    Call<List<SubDetailModal>> getSubDetails(@Query("type") String type);

}
