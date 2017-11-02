package com.example.windows.three;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

/**
 * Created by windows on 9/15/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        super.onCreate();
        broadcaster = LocalBroadcastManager.getInstance(getBaseContext());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Intent intent = new Intent("MY DATA");
            if(remoteMessage.getData().get("GenFlightDetail")!=null) {
                intent.putExtra("GenFlightDetail", remoteMessage.getData().get("GenFlightDetail"));
            }
            else
            {
                intent.putExtra("PerFlightDetail",remoteMessage.getData().get("PerFlightDetail"));
            }
            broadcaster.sendBroadcast(intent);

        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }}
