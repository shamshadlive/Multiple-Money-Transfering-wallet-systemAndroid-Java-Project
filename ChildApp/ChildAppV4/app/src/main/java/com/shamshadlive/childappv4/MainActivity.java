package com.shamshadlive.childappv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shamshadlive.childappv4.models.login.WalletBalance;
import com.shamshadlive.childappv4.retrofit.Apiservices;
import com.shamshadlive.childappv4.retrofit.Config;
import com.shamshadlive.childappv4.utils.AppDialogs;
import com.shamshadlive.childappv4.utils.SharedPrefHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView txt_welcomemsg,txt_currentBalance,btn_logout;
    ImageView imgbtn_receivemoney,imgbtn_sendmoney;
    AppDialogs Appdialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Appdialogs=new AppDialogs(this);
        txt_welcomemsg=findViewById(R.id.txt_welcomemsg);
        txt_currentBalance=findViewById(R.id.txt_currentBalance);
        imgbtn_receivemoney=findViewById(R.id.imgbtn_receivemoney);
        btn_logout=findViewById(R.id.btn_logout);
        imgbtn_sendmoney=findViewById(R.id.imgbtn_sendmoney);



        String firstname = SharedPrefHelper.getInstance(MainActivity.this).getFirstname();
        String username = SharedPrefHelper.getInstance(MainActivity.this).getSavedUsername();
        String childlogid = SharedPrefHelper.getInstance(MainActivity.this).getsaveLogId();


        txt_welcomemsg.setText(firstname);

        walletbalance(username);


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLogoutmsg();

            }
        });


        imgbtn_sendmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent myIntent = new Intent(MainActivity.this, SendMoneyActivity.class);
                MainActivity.this.startActivity(myIntent);



            }
        });

        imgbtn_receivemoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent tintent = new Intent(MainActivity.this,RecievemoneyActivity.class);
                startActivity(tintent);


            }
        });

    }


    public void showLogoutmsg(){


        Appdialogs.showLogoutWithMessage("Are You Sure Want To Logout ", new AppDialogs.OnDualActionButtonClickListener() {
            @Override
            public void onClickPositive(String id) {


                SharedPrefHelper.getInstance(MainActivity.this).clearsharedPreference();

                Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }

            @Override
            public void onClickNegetive(String id) {



            }
        });


    }

    public void walletbalance(String funusername){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);

        retrofit2.Call<WalletBalance> call= api.walletBalance(funusername);
        call.enqueue(new retrofit2.Callback<WalletBalance>() {
            @Override
            public void onResponse(retrofit2.Call<WalletBalance> call, retrofit2.Response<WalletBalance> response) {



                txt_currentBalance.setText(response.body().getBalance());


            }

            @Override
            public void onFailure(retrofit2.Call<WalletBalance> call, Throwable t) {

            }
        });

    }





}