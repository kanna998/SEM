package com.example.sem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    String result = "";

    Context ctx;

    Registration r = new Registration();

    public BackgroundWorker(Context context) {
        ctx = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String name = strings[0],dob = strings[1],gender = strings[2],email = strings[3];
        String mnumber = strings[4],consumerId = strings[5],password = strings[6],serviceNum = strings[7];
        String meterNum = strings[8];
        try {
            URL url = new URL("http://mysqlpro.000webhostapp.com/registration.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+
                    "&"+URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+
                    "&"+URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+
                    "&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+
                    "&"+URLEncoder.encode("mnumber","UTF-8")+"="+URLEncoder.encode(mnumber,"UTF-8")+
                    "&"+URLEncoder.encode("consumerId","UTF-8")+"="+URLEncoder.encode(consumerId,"UTF-8")+
                    "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+
                    "&"+URLEncoder.encode("serviceNum","UTF-8")+"="+URLEncoder.encode(serviceNum,"UTF-8")+
                    "&"+URLEncoder.encode("meterNum","UTF-8")+"="+URLEncoder.encode(meterNum,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

            String line = "";

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MalformedURLException Occured";
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException Occured";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Exception Occured";
        }
        return "Registration Successfull";
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Status");
//        r.showProgress(true);
    }

    @Override
    protected void onPostExecute(String result) {
        AlertDialog dialog=new AlertDialog.Builder(ctx).setTitle("Registration Status").setMessage(result+"\n"+"If Registation Failed Click on Retry").setPositiveButton("Ok",null).setNegativeButton("Retry",null).show();
        Button postive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        postive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.startActivity(new Intent(ctx,LoginActivity.class));
            }
        });

    /*  r.showProgress(false);
        alertDialog.setMessage(result);
        alertDialog.show();*/

    }


}
