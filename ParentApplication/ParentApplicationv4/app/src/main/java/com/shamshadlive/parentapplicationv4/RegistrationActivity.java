package com.shamshadlive.parentapplicationv4;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.login.LoginResponse;
import com.shamshadlive.parentapplicationv4.models.registration.GetRegistrationResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    EditText edt_Regparentfname,edt_upiPin,edt_Regparentlname, edt_Regparentmobile, edt_Regparentadhaar, edt_RegparentUsername, edt_RegparentPassword, edt_RegparentEmail, edt_RegparentRole;
    Button btn_register;
    TextView btn_userlogin;
    AppDialogs progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn_userlogin = findViewById(R.id.btn_userlogin);
        edt_Regparentfname = findViewById(R.id.edt_Regparentfname);
        edt_Regparentlname = findViewById(R.id.edt_Regparentlname);
        edt_Regparentmobile = findViewById(R.id.edt_Regparentmobile);
        edt_Regparentadhaar = findViewById(R.id.edt_Regparentadhaar);
        edt_RegparentUsername = findViewById(R.id.edt_RegparentUsername);
        edt_upiPin = findViewById(R.id.edt_upiPin);
        edt_RegparentPassword = findViewById(R.id.edt_RegparentPassword);
        edt_RegparentEmail = findViewById(R.id.edt_RegparentEmail);
        btn_register = findViewById(R.id.btn_register);
        progressbar= new AppDialogs(RegistrationActivity.this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressbar.showProgressBar();

                registerfunction();


            }
        });


        btn_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                RegistrationActivity.this.startActivity(myIntent);
            }
        });


    }


    public void registerfunction() {



        String firstname=edt_Regparentfname.getText().toString();
        String lastname=edt_Regparentlname.getText().toString();
        String username=edt_RegparentUsername.getText().toString();
        String password=edt_RegparentPassword.getText().toString();
        String email=edt_RegparentEmail.getText().toString();
        String mobile=edt_Regparentmobile.getText().toString();
        String adharnumber=edt_Regparentadhaar.getText().toString();
        String upiPin=edt_upiPin.getText().toString();

        String role="P";
        String profilepicurl="#";
        String noofchild="0";
        String status="1";
        String upiId=username+"@parentwallet";





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        retrofit2.Call<GetRegistrationResponse> call = api.getregdata(
                username,
                password,
                email,
                role,
                profilepicurl,
                noofchild,
                firstname,
                adharnumber,
                lastname,
                mobile,
                upiPin,
                status,
                upiId);

        call.enqueue(new retrofit2.Callback<GetRegistrationResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetRegistrationResponse> call, retrofit2.Response<GetRegistrationResponse> response) {

                Log.e("---------", "onResponse: "+response );
                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        String message = response.body().getMessage();
                        Log.e("-----------", "onResponse: "+message );

                        if (message.equals(Constants.CODE_SUCCESS)) {

                            progressbar.hideProgressbar();

                            showToast("Registration is Sucess");

                            Intent myIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            RegistrationActivity.this.startActivity(myIntent);
                            RegistrationActivity.this.finish();

                        } else if (message.equals(Constants.CODE_FAILED)) {

                            showToast("Something Went Wrong");

                        }
                    }
                }
            }


            @Override
            public void onFailure(retrofit2.Call<GetRegistrationResponse> call, Throwable t) {

                Log.e("TAG", "onFailure: "+t);
               showToast("Something wrong");

            }
        });


    }


    public void showToast(String message) {

        Toast.makeText(RegistrationActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

}
