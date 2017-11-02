package com.example.windows.three;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by windows on 9/14/2017.
 */

public class GenAnnouncement extends Fragment {

    ArrayAdapter<String> adapter;
    ArrayList<String> listitems;
    ListView listView;

    private static final String TAG ="GenAnnouncement";
    Toast toast;
    EditText tokentext;
    View rootView;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        FirebaseMessaging.getInstance().subscribeToTopic("GenFlightDetail");
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onClick: "+token);

        rootView = inflater.inflate(R.layout.genannouncement,container,false);
        listitems=new ArrayList<String>();
        listView=(ListView)rootView.findViewById(R.id.list1a);
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.test_list_item,listitems);
        listView.setAdapter(adapter);
        if(getActivity().getIntent().getExtras()!=null){
            if(getActivity().getIntent().getExtras().getString("GenFlightDetail")!=null){
                listitems.add(getActivity().getIntent().getExtras().getString("GenFlightDetail"));
            }
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MY DATA"));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras().getString("GenFlightDetail")!=null){
                listitems.add(intent.getExtras().getString("GenFlightDetail"));

            }
            adapter.notifyDataSetChanged();
        }
    };
}