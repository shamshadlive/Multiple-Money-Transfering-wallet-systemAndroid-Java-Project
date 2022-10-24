package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.getchildregistrationresponse.GetChildRegistrationResponse;
import com.shamshadlive.parentapplicationv4.models.registration.GetRegistrationResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;
import com.shamshadlive.parentapplicationv4.utils.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class AddChildActivity extends AppCompatActivity {

    EditText edt_addchildDob,edt_addchildfname,edt_ChildupiPIn,edt_addchildlusername,edt_addchildlname,edt_addchildPassword,edt_mobile,edt_addchildAdhaar,edt_email;
    Button btn_addChild,btn_goback;
    ImageView btn_datechoose;
AppDialogs progressbar;
    String parent_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchild);

        edt_addchildfname=findViewById(R.id.edt_addchildfname);
        btn_datechoose=findViewById(R.id.btn_datechoose);
        edt_addchildlname=findViewById(R.id.edt_addchildlname);
        edt_addchildlusername=findViewById(R.id.edt_addchildlusername);
        edt_addchildPassword=findViewById(R.id.edt_addchildPassword);
        edt_addchildAdhaar=findViewById(R.id.edt_addchildAdhaar);
        edt_addchildDob=findViewById(R.id.edt_addchildDob);
        btn_addChild=findViewById(R.id.btn_addChild);
        btn_goback=findViewById(R.id.btn_goback);
        edt_email=findViewById(R.id.edt_email);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_ChildupiPIn=findViewById(R.id.edt_ChildupiPIn);
        edt_addchildDob=findViewById(R.id.edt_addchildDob);
        progressbar= new AppDialogs(AddChildActivity.this);



        btn_addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressbar.showProgressBar();
                addnewchild();


            }
        });


        btn_datechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utility.showDatePicker(edt_addchildDob,AddChildActivity.this);


            }
        });
        edt_addchildDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utility.showDatePicker(edt_addchildDob,AddChildActivity.this);


            }
        });




        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


    }



    public void addnewchild(){


        String firstname=edt_addchildfname.getText().toString();
        String lastname=edt_addchildlname.getText().toString();
        String username=edt_addchildlusername.getText().toString();
        String password=edt_addchildPassword.getText().toString();
        String email=edt_email.getText().toString();
        String mobile=edt_mobile.getText().toString();
        String adharnumber=edt_addchildAdhaar.getText().toString();
        String upiPin=edt_ChildupiPIn.getText().toString();
        String dateofbirth=edt_addchildDob.getText().toString();

        String role="C";
        String profilepicurl="#";

        String status="1";
        String upiId=username+"@childwallet";

        parent_id = SharedPrefHelper.getInstance(AddChildActivity.this).getsaveparenttableId();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        retrofit2.Call<GetChildRegistrationResponse> call = api.getchildregrepdata(
                username,
                password,
                email,
                role,
                status,
                upiId,
                parent_id,
                firstname,
                lastname,
                profilepicurl,
                adharnumber,
                mobile,
                dateofbirth,
                upiPin);

        call.enqueue(new retrofit2.Callback<GetChildRegistrationResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetChildRegistrationResponse> call, retrofit2.Response<GetChildRegistrationResponse> response) {

                Log.e("---------", "onResponse: "+response );
                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        String message = response.body().getMessage();
                        Log.e("-----------", "onResponse: "+message );

                        if (message.equals(Constants.CODE_SUCCESS)) {

                            progressbar.hideProgressbar();

                            showToast("Registration is Sucess");

                           /* Intent myIntent = new Intent(AddChildActivity.this, MainActivity.class);
                            AddChildActivity.this.startActivity(myIntent);
                            AddChildActivity.this.finish();*/

                            onBackPressed();

                        } else if (message.equals(Constants.CODE_FAILED)) {

                            showToast("Something Went Wrong");

                        }
                    }
                }
            }


            @Override
            public void onFailure(retrofit2.Call<GetChildRegistrationResponse> call, Throwable t) {

                Log.e("TAG", "onFailure: "+t);
                showToast("Something wrong");

            }
        });





    }


    public void showToast(String message) {

        Toast.makeText(AddChildActivity.this,
                message, Toast.LENGTH_LONG).show();
    }




}