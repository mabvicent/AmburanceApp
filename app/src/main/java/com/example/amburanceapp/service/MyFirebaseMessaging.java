package com.example.amburanceapp.service;

import android.content.Intent;
import android.util.Log;

import com.example.amburanceapp.PatientCall;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("HEALTH APP", remoteMessage.getNotification().getBody());


        LatLng customer_location = new Gson().fromJson(remoteMessage.getNotification().getBody(),LatLng.class);

        Intent customer = new Intent(getBaseContext(), PatientCall.class);
        customer.putExtra("lat",customer_location.latitude);
        customer.putExtra("log",customer_location.longitude);
        startActivity(customer);

        customer.putExtra( "patient",remoteMessage.getNotification().getTitle() );







    }
}
