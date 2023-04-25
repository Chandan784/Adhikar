package com.adhikar.adhikar.Modal;

import com.adhikar.adhikar.Interface.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    public  static Controller controler;
    public  static  String BASE_URL = "https://educhandan.com/api/";
    public static Retrofit retrofit;

    public Controller() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized  Controller getInstance(){

        if (controler==null){
            controler= new Controller();

        }
        return controler;
    }

   public Api getApi(){

        return retrofit.create(Api.class);
    }

}
