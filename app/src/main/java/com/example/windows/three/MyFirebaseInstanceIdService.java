package com.example.windows.three;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by windows on 9/15/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token" + token);
        sendTokenToServer(token);
    }
    public void sendTokenToServer(String token){
      /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ID");
        myRef.push().setValue(token);*/
    }
}
