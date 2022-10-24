package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.models.Childremove;
import com.shamshadlive.parentapplicationv4.models.WalletBalance;
import com.shamshadlive.parentapplicationv4.models.getchild.GetChildDataResponse;
import com.shamshadlive.parentapplicationv4.models.getchildwalletbalance.GetChildwalletResponse;
import com.shamshadlive.parentapplicationv4.models.getusername.GetUsernameResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChildInterfaceActivity extends AppCompatActivity {

    TextView txt_childviewname, txt_childviewbalance, txt_childviewusername;
    ImageView btn_childviewaddmoney,btn_childviewtransactions;
    Button btn_childviewremovechild, btn_childviewgohome;
    AppDialogs Appdialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childinterface);

        txt_childviewname = findViewById(R.id.txt_childviewname);
        txt_childviewbalance = findViewById(R.id.txt_childviewbalance);
        txt_childviewusername = findViewById(R.id.txt_childviewusername);
        btn_childviewaddmoney = findViewById(R.id.btn_childviewaddmoney);
        btn_childviewgohome = findViewById(R.id.btn_childviewgohome);
        btn_childviewremovechild = findViewById(R.id.btn_childviewremovechild);
        btn_childviewtransactions = findViewById(R.id.btn_childviewtransactions);

        Appdialogs=new AppDialogs(this);
        String childlogid = getIntent().getStringExtra("childlogid");


        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");


        txt_childviewname.setText(firstname + " " + lastname);


       /* getWalletBalance(childlogid);
        getusername(childlogid);*/


        btn_childviewaddmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChildInterfaceActivity.this, ChildInAddMoneyActivity.class);
                intent.putExtra("childretrive",childlogid);
                startActivity(intent);


            }
        });


        btn_childviewgohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();


            }
        });

        btn_childviewtransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChildInterfaceActivity.this, ChildInTransactionActivity.class);
                intent.putExtra("childretrive",childlogid);
                startActivity(intent);




            }
        });

        btn_childviewremovechild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                showRemoveChildmsg(childlogid);
               /* Removechild(childlogid);
                onBackPressed();*/


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        String childlogid = getIntent().getStringExtra("childlogid");
        getWalletBalance(childlogid);
        getusername(childlogid);

    }

    public void showToast(String message) {

        Toast.makeText(ChildInterfaceActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

    public void getWalletBalance(String _childlogid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        retrofit2.Call<GetChildwalletResponse> call = api.getChildwallet(_childlogid);
        call.enqueue(new retrofit2.Callback<GetChildwalletResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetChildwalletResponse> call, retrofit2.Response<GetChildwalletResponse> response) {


                String currentbalance = response.body().getBalance();
                txt_childviewbalance.setText(currentbalance + " ₹");


            }

            @Override
            public void onFailure(retrofit2.Call<GetChildwalletResponse> call, Throwable t) {

                showToast("Failed to Retrive data");

            }
        });

    }

    public void getusername(String id) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api = retrofit.create(Apiservices.class);

        retrofit2.Call<GetUsernameResponse> call = api.getusername(id);
        call.enqueue(new retrofit2.Callback<GetUsernameResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetUsernameResponse> call, retrofit2.Response<GetUsernameResponse> response) {


                String username = response.body().getData().getUsername();
                String childupiid = response.body().getData().getUpiId();
                SharedPrefHelper.getInstance(ChildInterfaceActivity.this).savechildupiId(childupiid);
                txt_childviewusername.setText(username);


            }

            @Override
            public void onFailure(retrofit2.Call<GetUsernameResponse> call, Throwable t) {

                showToast("Failed to Retrive data");

            }
        });

    }


    public void Removechild(String _childlogid) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Apiservices api = retrofit.create(Apiservices.class);

            retrofit2.Call<Childremove> call = api.removechild(_childlogid);
            call.enqueue(new retrofit2.Callback<Childremove>() {
                @Override
                public void onResponse(retrofit2.Call<Childremove> call, retrofit2.Response<Childremove> response) {


                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            Childremove Childremoveres = response.body();

                            if (Childremoveres.getMessage() != null) {

                                showToast("Child Data Deleted");
                            }


                        }
                    }


                }

                @Override
                public void onFailure(retrofit2.Call<Childremove> call, Throwable t) {

                    showToast("Failed");
                }
            });


        }



    public void showRemoveChildmsg(String getchildlogid){


        Appdialogs.showLogoutWithMessage("Are You Sure Want To Delete ", new AppDialogs.OnDualActionButtonClickListener() {
            @Override
            public void onClickPositive(String id) {


                Removechild(getchildlogid);
                onBackPressed();



            }

            @Override
            public void onClickNegetive(String id) {



            }
        });


    }
    }
