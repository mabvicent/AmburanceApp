package com.example.amburanceapp.remote;

import com.example.amburanceapp.model.FCMResponse;
import com.example.amburanceapp.model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {


    @Headers({
            "Authorisation:key=<AAAAAy8zPYhU:APA91bFSyO2ia9j10kYVmYBB9xh3pzG_yfEnGL-9lotd0R6ZWenDOEaZupwgT0FUpOV5OLeZls18ChYglUb8m7ZWYTj8Q5pp-fhoYrtJGj8xGIYH2NGKGBxnTkLKTzUloKD6KQtjAHKm>",
            "Content-Type:application/json"


    })
    @POST("fcm/send")
    Call<FCMResponse> sendMessage(@Body Sender body);
}

