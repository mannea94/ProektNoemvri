package com.example.manne.proektnoemvri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonLOG)
    Button buttonLOG;

    @BindView(R.id.textGuest)
    TextView textGuest;
    User user;

    static int REQUEST_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        user = new User();
    }

    @OnClick (R.id.buttonLOG)
    public void onClickLog(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("LogIn", true );
        startActivity(intent);
    }

    @OnClick (R.id.textGuest)
    public void onClickGuest(View view){
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("Guest", new User());
//        startActivityForResult(intent, REQUEST_CODE);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE){
            if(data.hasExtra("Guest")){
//              userName=user.getUserName();
            }
        }
    }



}
