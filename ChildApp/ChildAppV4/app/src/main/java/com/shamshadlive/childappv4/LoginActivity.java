package com.shamshadlive.childappv4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shamshadlive.childappv4.common.Constants;
import com.shamshadlive.childappv4.models.login.GetChildLoginResponse;
import com.shamshadlive.childappv4.retrofit.Apiservices;
import com.shamshadlive.childappv4.retrofit.Config;
import com.shamshadlive.childappv4.utils.SharedPrefHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edt_childUsername, edt_childPassword;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        edt_childUsername = findViewById(R.id.edt_childUsername);
        edt_childPassword = findViewById(R.id.edt_childPassword);
        btn_login = findViewById(R.id.btn_login);


        checkAutoSave();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();



            }
        });




    }


    public void checkAutoSave() {

        String username = SharedPrefHelper.getInstance(LoginActivity.this).getSavedUsername();
        String password = SharedPrefHelper.getInstance(LoginActivity.this).getSavedPassword();


        if (!username.isEmpty() && !password.isEmpty()) {

            edt_childUsername.setText(username);
            edt_childPassword.setText(password);
            login();

        }


    }


    public void login() {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        String username = edt_childUsername.getText().toString();
        String password = edt_childPassword.getText().toString();


        retrofit2.Call<GetChildLoginResponse> call = api.loginapichild(username, password);
        call.enqueue(new retrofit2.Callback<GetChildLoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetChildLoginResponse> call, retrofit2.Response<GetChildLoginResponse> response) {


                Log.e("------", "onres: "+response);

                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        GetChildLoginResponse userData = response.body();

                        String message = userData.getMessage();

                        if (message.equals(Constants.CODE_INVALID_USERNAME)) {

                            showToast("Username is wrong");

                        } else if (message.equals(Constants.CODE_INVALID_PASSWORD)) {

                            showToast("Password is wrong");



                        } else {




                            SharedPrefHelper.getInstance(LoginActivity.this).saveLogin(username, password);

                            showToast("Login Sucess");

                            String firstname = userData.getData().getFirstname();
                            String Childlog_ID = userData.getData().getLogId().toString();

                            SharedPrefHelper.getInstance(LoginActivity.this).saveFirstname(firstname);
                            SharedPrefHelper.getInstance(LoginActivity.this).saveLogId(Childlog_ID);

                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(myIntent);
                            LoginActivity.this.finish();

                        }
                    }

                } else {
                    Toast.makeText(LoginActivity.this,
                            "unknown error", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(retrofit2.Call<GetChildLoginResponse> call, Throwable t) {

                Log.e("------", "onFailure: "+t );
                Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void showToast(String message) {

        Toast.makeText(LoginActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

}





