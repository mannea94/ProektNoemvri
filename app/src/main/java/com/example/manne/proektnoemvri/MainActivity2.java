package com.example.manne.proektnoemvri;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.editUserName)
    EditText editUserName;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.buttonNext)
    Button buttonNext;

    RadioButton choice;
    char pol;

    static int REQUEST_CODE=1000;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        user = new User();
        Intent edit = getIntent();
        if(edit.hasExtra("User")){
            user = (User) edit.getSerializableExtra("User");
            editName.setText(user.getIme());
            editLastName.setText(user.getLastName());
            editUserName.setText(user.getUserName());
            pol=user.getGender();
            if(pol=='F'){
                radioButton1.setChecked(true);
            }
            else{
                radioButton2.setChecked(true);
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                choice = (RadioButton) findViewById(selectedID);
                if(choice.getText().toString().equals("F")){
                    pol='F';
                }
                if(choice.getText().toString().equals("M")){
                    pol='M';
                }
            }
        });


    }


//    @OnClick({R.id.radioButton1, R.id.radioButton2})
//    public void onRadioButtonClicked(RadioGroup radioB){
//        if(radioButton1.isChecked()){
//
//        }
//        if(radioButton2.isChecked()){
//
//        }
//    }

    @OnClick(R.id.buttonNext)
    public void onClickNext(View view){
        if(!editName.getText().toString().isEmpty() && !editLastName.getText().toString().isEmpty() && !editUserName.getText().toString().isEmpty()){
            user.setIme(editName.getText().toString());
            user.setLastName(editLastName.getText().toString());
            user.setUserName(editUserName.getText().toString());
            user.setGender(pol);
        }
        else{
            if(editName.getText().toString().isEmpty())
            editName.setError("Put Name");
            if(editLastName.getText().toString().isEmpty())
            editLastName.setError("Put Last Name");
            if(editUserName.getText().toString().isEmpty())
            editUserName.setError("Put User Name");
        }
        Intent intent = new Intent(this, MainActivity3.class);

//        startActivityForResult(intent,REQUEST_CODE);
        if(getIntent().hasExtra("LogIn")) {
            intent.putExtra("User", user);
            startActivity(intent);
        } else if (getIntent().hasExtra("Edit")) {

            intent.putExtra("EditUser", user);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            intent.putExtra("User", user);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE){
            user = (User) data.getSerializableExtra("User");
        }
    }

}
