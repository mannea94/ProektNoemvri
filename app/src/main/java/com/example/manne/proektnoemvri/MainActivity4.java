package com.example.manne.proektnoemvri;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity4 extends AppCompatActivity {

    @BindView(R.id.buttonCheck)
    Button buttonCheck;
    @BindView(R.id.textView)
    EditText textView;
    @BindView(R.id.buttonBack)
    Button buttonBack;

    NetworkChangeReceiver myNetworkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

//        textView.setText("Internet is connected fine");

    }

    @OnClick(R.id.buttonCheck)
    public void onClickCheck(View view){
        myNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(myNetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }



    @OnClick(R.id.buttonBack)
    public void onClickBack(View view){
        if(textView.getText().length()>0){
            String text = textView.getText().toString();
            Intent data = new Intent().putExtra("Connection", text);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override protected void onPause() {
        if(myNetworkChangeReceiver!=null)
            unregisterReceiver(myNetworkChangeReceiver);
        super.onPause();
    }

}
