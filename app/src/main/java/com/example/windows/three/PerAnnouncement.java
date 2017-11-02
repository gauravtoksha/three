package com.example.windows.three;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by windows on 9/14/2017.
 */

public class PerAnnouncement extends Fragment {
    String token;
    FirebaseDatabase database;
    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayList<String> listitems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView=inflater.inflate(R.layout.perannouncement,container,false);
        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = getLayoutInflater(null);
                View mView = layoutInflaterAndroid.inflate(R.layout.per_diaglogbox,null);
                AlertDialog.Builder Dialog= new AlertDialog.Builder(getActivity());
                Dialog.setView(mView);
                final EditText ticketname=(EditText)mView.findViewById(R.id.ticketname);
                final TextView enterticket=(TextView)mView.findViewById(R.id.textView3);
                final EditText ticketno=(EditText)mView.findViewById(R.id.ticketno);
                Dialog.setCancelable(false);
                Dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        token=FirebaseInstanceId.getInstance().getToken();
                        database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Ticket");
                        TicketDetails td=new TicketDetails();
                        td.setName(ticketname.getText().toString());
                        td.setToken(token);
                        td.setId(ticketno.getText().toString());
                        myRef.push().setValue(td);
                    }
                });
                Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=Dialog.create();
                alertDialog.show();
            }
        });


        listitems=new ArrayList<String>();

        listView1=(ListView)rootView.findViewById(R.id.list1b);
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.test_list_item,listitems);
        listView1.setAdapter(adapter);
        if(getActivity().getIntent().getExtras()!=null){
            if(getActivity().getIntent().getExtras().getString("PerFlightDetail")!=null){
                listitems.add(getActivity().getIntent().getExtras().getString("PerFlightDetail"));
            }
        }
        return rootView;
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
            if(intent.getExtras().getString("PerFlightDetail")!=null){
                listitems.add(intent.getExtras().getString("PerFlightDetail"));

            }
            adapter.notifyDataSetChanged();

        }
    };
}
