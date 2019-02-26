package com.example.sem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;




public class Registration extends AppCompatActivity {

    AlertDialog alertDialog;
    EditText edit_name,edit_mnumber,edit_dob,edit_email_id,edit_consumer_id,edit_password,edit_servicenum,edit_meternum;
    RadioButton radioButton;
    RadioGroup radioGroup;
    public String name,dob,gender,email,mnumber,consumerId,password, serviceNum,meterNum;
    View mProgressView;
    private static int SPLASH_TIME_OUT = 2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edit_name=findViewById(R.id.edit_name);
        edit_dob=findViewById(R.id.edit_dob);
        radioGroup=findViewById(R.id.radioGroup);
    }

    public void next1(View view){
        int radio_id=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radio_id);
        gender=radioButton.getText().toString();
        name=edit_name.getText().toString();
        dob=edit_dob.getText().toString();
        setContentView(R.layout.registration1);
    }

    public void next2(View view){
        edit_email_id=findViewById(R.id.edit_email_id);
        edit_mnumber=findViewById(R.id.edit_mnumber);
        email=edit_email_id.getText().toString();
        mnumber=edit_mnumber.getText().toString();
        setContentView(R.layout.registration2);
    }
    public void next3(View view){
        edit_consumer_id=findViewById(R.id.edit_consumer_id);
        edit_password=findViewById(R.id.edit_password);
        consumerId=edit_consumer_id.getText().toString();
        password=edit_password.getText().toString();
        setContentView(R.layout.registration3);
    }
    public void onReg(View view) {
        edit_servicenum = findViewById(R.id.edit_servicenum);
        edit_meternum = findViewById(R.id.edit_meternum);
        serviceNum = edit_servicenum.getText().toString();
        meterNum = edit_meternum.getText().toString();
        Toast.makeText(getApplication(), "name = " + name + "/n dob=" + dob + "/n gender=" + gender + "/n mail=" + email + "/n phno=" + mnumber + "/n " +
                "cus num" + consumerId + "/n pass=" + password + "/n ser num=" + serviceNum + "/n met num=" + meterNum, Toast.LENGTH_SHORT).show();
        BackgroundWorker obj = new BackgroundWorker(this);
        obj.execute(name, dob, gender, email, mnumber, consumerId, password, serviceNum, meterNum);

    }


}

