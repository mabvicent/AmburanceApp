package com.example.amburanceapp.remote;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

private static Retrofit retrofit = null;

public static Retrofit getClint(String baseURL){

    if(retrofit==null){

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }
    return retrofit;
}

}
