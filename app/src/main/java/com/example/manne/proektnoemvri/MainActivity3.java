package com.example.manne.proektnoemvri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.manne.proektnoemvri.MainActivity.REQUEST_CODE;

public class MainActivity3 extends AppCompatActivity {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.editUser)
    Button editUser;
    @BindView(R.id.addUser)
    Button addUser;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.buttonCheck)
    Button buttonCheck;
    @BindView(R.id.imageView)
    ImageView imageView;

    ArrayList<User> userLista;

    static int REQUEST_QODE=1000;
    static int REQUEST_CODE_USER=1001;

    RadioButton choice;

    char pol;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        user = new User();
        userLista = new ArrayList<User>();

        textView2.setText("Internet not checked yet");



        Intent intent = getIntent();
        if(intent.hasExtra("User")){
            user = (User) intent.getSerializableExtra("User");
            userLista.add(user);
            updateData();

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                choice = (RadioButton) findViewById(selectedID);
                if(choice.getText().toString().equals("F")){
                        userLista.get((int)spinner.getSelectedItemId()).gender='F';
                        imageView.setImageResource(R.drawable.mujer);
                }
                if(choice.getText().toString().equals("M")){
                        userLista.get((int)spinner.getSelectedItemId()).gender='M';
                        imageView.setImageResource(R.drawable.man);
                }
            }
        });

        Intent guest = getIntent();
        if(guest.hasExtra("Guest")){
            user = (User) intent.getSerializableExtra("Guest");
            userLista.add(user);
            updateData();
        }



        ArrayAdapter<User> dataAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_dropdown_item, userLista);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                user = userLista.get(i);
                updateData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void updateData(){
        textView1.setText(user.ime + "\n" + user.lastName);
        pol = user.getGender();
        if(pol=='F'){
            radioButton1.setChecked(true);
            imageView.setImageResource(R.drawable.mujer);
        }
        if(pol=='M'){
            radioButton2.setChecked(true);
            imageView.setImageResource(R.drawable.man);
        }
    }

    @OnClick(R.id.editUser)
    public void onClickEdit(View view){
//        if(!textView1.getText().toString().isEmpty()){
//            textView1.setText(user.toString());
//        }

        Intent edit = new Intent(this, MainActivity2.class);
        edit.putExtra("User", user);
        edit.putExtra("Edit", true);
        startActivityForResult(edit, REQUEST_CODE_USER);

//        Intent data = new Intent().putExtra("User", user);
//        setResult(RESULT_OK, data);
//        finish();
    }

    @OnClick(R.id.addUser)
    public void onClickAdd(View view){
//        Intent data = new Intent().putExtra("Guest", user.getUserName().toString());
        Intent guest = new Intent(this, MainActivity2.class);
        startActivityForResult(guest, REQUEST_CODE_USER);
    }

    @OnClick(R.id.buttonCheck)
    public void onClickCheck(View view){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivityForResult(intent, REQUEST_QODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE){
            if(data.hasExtra("Connection")){
            textView2.setText(data.getExtras().getString("Connection"));
            }
        }

        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE_USER){
            if(data.hasExtra("User")){
                user = (User) data.getSerializableExtra("User");
                userLista.add(user);
                updateData();
            }
            if(data.hasExtra("EditUser")) {
                user = (User) data.getSerializableExtra("EditUser");
                userLista.get((int)spinner.getSelectedItemId()).ime=user.getIme();
                userLista.get((int)spinner.getSelectedItemId()).lastName=user.getLastName();
                userLista.get((int)spinner.getSelectedItemId()).userName=user.getUserName();
                userLista.get((int)spinner.getSelectedItemId()).gender=user.getGender();
                updateData();
            }
        }

    }

}
