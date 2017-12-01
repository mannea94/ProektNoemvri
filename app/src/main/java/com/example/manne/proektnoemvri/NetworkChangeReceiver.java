package com.example.manne.proektnoemvri;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by manne on 25.11.2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "NetworkChangeReceiver";
    private boolean isConnected=false;
    AlertDialog.Builder builder;

    @Override
    public void onReceive(final Context context, Intent intent) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you connected");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MainActivity4)context).textView.setText(isNetworkAvailable(context));
            }
        });
        builder.setMessage(isNetworkAvailable(context));
        builder.create().show();
    }



    private String isNetworkAvailable(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if(activeNetwork!= null){
                if(activeNetwork.getState() == NetworkInfo.State.CONNECTED){
                    if(!isConnected){
                        isConnected = true;
                    }
                    return "Now you are connected to the internet";
                }
            }
        }
        isConnected=false;
        return "You are not connected to the internet";
    }
}
