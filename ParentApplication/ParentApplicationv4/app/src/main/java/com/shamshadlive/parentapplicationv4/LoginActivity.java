package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.login.LoginResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edt_parentUsername, edt_parentPassword;
    Button btn_login;
    TextView btn_userSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        btn_userSignup = findViewById(R.id.btn_userSignup);
        edt_parentUsername = findViewById(R.id.edt_parentUsername);
        edt_parentPassword = findViewById(R.id.edt_parentPassword);
        btn_login = findViewById(R.id.btn_login);


        checkAutoSave();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
                //loginfunction();


            }
        });

        btn_userSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });


    }


    public void checkAutoSave() {

        String username = SharedPrefHelper.getInstance(LoginActivity.this).getSavedUsername();
        String password = SharedPrefHelper.getInstance(LoginActivity.this).getSavedPassword();


        if (!username.isEmpty() && !password.isEmpty()) {

            edt_parentUsername.setText(username);
            edt_parentPassword.setText(password);
            login();

        }


    }


    public void login() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        retrofit2.Call<LoginResponse> call = api.loginapitest(edt_parentUsername.getText().toString(), edt_parentPassword.getText().toString());
        call.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        LoginResponse userData = response.body();

                        String message = userData.getMessage();

                        if (message.equals(Constants.CODE_INVALID_USERNAME)) {

                            showToast("Username is wrong");

                        } else if (message.equals(Constants.CODE_INVALID_PASSWORD)) {

                            showToast("Password is wrong");



                        } else {



                            String username = edt_parentUsername.getText().toString();
                            String password = edt_parentPassword.getText().toString();


                            SharedPrefHelper.getInstance(LoginActivity.this).saveLogin(username, password);

                            showToast("Login Sucess");

                            String firstname = userData.getData().getFirstname();
                            String Parentlog_ID = userData.getData().getLogId().toString();
                            String ParenttableID = userData.getData().getId().toString();

                            SharedPrefHelper.getInstance(LoginActivity.this).saveFirstname(firstname);
                            SharedPrefHelper.getInstance(LoginActivity.this).saveLogId(Parentlog_ID);
                            SharedPrefHelper.getInstance(LoginActivity.this).saveparenttableId(ParenttableID);

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
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {

                Log.e("----", "onFailure: "+t );
                Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void showToast(String message) {

        Toast.makeText(LoginActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

}
    /*public void loginretrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);

        retrofit2.Call<LoginResponse> call= api.loginapi(edt_parentUsername.getText().toString(),edt_parentPassword.getText().toString());
        call.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {



                String message=response.body().getMessage();

                if (message.equals(Constants.CODE_INVALID_USERNAME))
                {
                    Log.e("---------", "onResponse: "+response );
                    showToast("Username is wrong");

                }

                else if(message.equals(Constants.CODE_INVALID_PASSWORD))
                {
                    Log.e("---------", "onResponse: "+response );
                    showToast("Password is wrong");
                }


                else{

                    Log.e("---------", "onResponse: "+response );

                    String username=edt_parentUsername.getText().toString();
                    String password=edt_parentPassword.getText().toString();

                    SharedPrefHelper.getInstance(LoginActivity.this).saveLogin(username,password);

                    showToast("Login Sucess");


                    String firstname=response.body().getFirstname();
                    String upiId=response.body().getFirstname();

                    SharedPrefHelper.getInstance(LoginActivity.this).saveFirstname(firstname);

                    Log.e("---------", "onResponse: "+firstname );
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                    finish();

                }







            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();

            }
        });

    }*/




