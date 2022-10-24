package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.adapter.ChildAdapter;
import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.WalletBalance;
import com.shamshadlive.parentapplicationv4.models.getchild.ChildKeyData;
import com.shamshadlive.parentapplicationv4.models.getchild.GetChildDataResponse;
import com.shamshadlive.parentapplicationv4.models.getupi.GetUpiResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ImageView imgbtn_sendmoney,imgbtn_transactions,imgbtn_receivemoney;
    TextView txt_currentBalance,txt_welcomemsg;
    Button btn_logout,btn_addchildHome,imgbtn_addmoneywallet;
    AppDialogs Appdialogs;
    RecyclerView childrecycleview;
    List<ChildKeyData> childItemList;
    String ParenttableID,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childItemList =new ArrayList<>();

        Appdialogs=new AppDialogs(this);
        imgbtn_sendmoney=findViewById(R.id.imgbtn_sendmoney);
        imgbtn_receivemoney=findViewById(R.id.imgbtn_receivemoney);
        imgbtn_addmoneywallet=findViewById(R.id.imgbtn_addmoneywallet);
        btn_addchildHome=findViewById(R.id.btn_addchildHome);
        imgbtn_transactions=findViewById(R.id.imgbtn_transactions);
        txt_currentBalance=findViewById(R.id.txt_currentBalance);
        txt_welcomemsg=findViewById(R.id.txt_welcomemsg);
        btn_logout=findViewById(R.id.btn_logout);


        String firstname = SharedPrefHelper.getInstance(MainActivity.this).getFirstname();
        username = SharedPrefHelper.getInstance(MainActivity.this).getSavedUsername();
        String parentlogid = SharedPrefHelper.getInstance(MainActivity.this).getsaveLogId();
        ParenttableID = SharedPrefHelper.getInstance(MainActivity.this).getsaveparenttableId();
        String upiID = SharedPrefHelper.getInstance(MainActivity.this).getSavedupiId();



//geting child data


        if (upiID.isEmpty()){

            getupi(username);
        }




        txt_welcomemsg.setText(firstname);




        imgbtn_addmoneywallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddMoneyActivity.class);
                startActivity(intent);


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



        imgbtn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoTransactions();

            }
        });


        btn_addchildHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent dintent = new Intent(MainActivity.this,AddChildActivity.class);
                startActivity(dintent);


            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLogoutmsg();



            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();





        getchilddata(ParenttableID);
        walletbalanceretrofit(username);


    }

    public void walletbalanceretrofit(String funusername){

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



public void gotoTransactions(){

    Intent tintent = new Intent(MainActivity.this,TransactionsActivity.class);
    startActivity(tintent);

}


public void getchilddata(String parent_idpass){

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Apiservices api=retrofit.create(Apiservices.class);

    retrofit2.Call<GetChildDataResponse> call= api.getchilddata(parent_idpass);
    call.enqueue(new retrofit2.Callback<GetChildDataResponse>() {
        @Override
        public void onResponse(retrofit2.Call<GetChildDataResponse> call, retrofit2.Response<GetChildDataResponse> response) {


            if (response.isSuccessful()) {

                if (response.body() != null) {

                    GetChildDataResponse getChildDataResponse= response.body();

                    if (getChildDataResponse.getData()!=null){

                        childItemList.clear();
                        childItemList.addAll(getChildDataResponse.getData());

                        getchildrecycleview();
                        showToast("Child Data Retirved");
                    }





                }
            }



        }

        @Override
        public void onFailure(retrofit2.Call<GetChildDataResponse> call, Throwable t) {

            showToast("Failed");
        }
    });



}

    public void showToast(String message) {

        Toast.makeText(MainActivity.this,
                message, Toast.LENGTH_LONG).show();
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


    public void getchildrecycleview(){


        childrecycleview=findViewById(R.id.childrecycleview);


        ChildAdapter childAdapter = new ChildAdapter(childItemList, new ChildAdapter.OnClickItem() {
            @Override
            public void onclickitem(ChildKeyData child) {



               String firstname = child.getFirstname();
               String lastname = child.getLastname();
               String childlogid = child.getLogId().toString();




               gotochildinterface(firstname,lastname,childlogid);




            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        childrecycleview.setLayoutManager(linearLayoutManager);

        childrecycleview.setAdapter(childAdapter);


    }


    public void gotochildinterface(String firstname,String lastname,String childlogid ){

        Intent myIntent = new Intent(MainActivity.this, ChildInterfaceActivity.class);

        myIntent.putExtra("firstname",firstname);
        myIntent.putExtra("lastname",lastname);
        myIntent.putExtra("childlogid",childlogid);

        MainActivity.this.startActivity(myIntent);


    }

    @Override
    public void onBackPressed() {


        exitmsg();
    }


    public void exitmsg(){


        Appdialogs.showLogoutWithMessage("Are You Sure Want To Exit ", new AppDialogs.OnDualActionButtonClickListener() {
            @Override
            public void onClickPositive(String id) {




                finish();



            }

            @Override
            public void onClickNegetive(String id) {



            }
        });


    }



    public void getupi(String usernamepass){





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);

        retrofit2.Call<GetUpiResponse> call= api.getupidata(usernamepass);
        call.enqueue(new retrofit2.Callback<GetUpiResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetUpiResponse> call, retrofit2.Response<GetUpiResponse> response) {


                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        String message = response.body().getMessage();

                        if (message.equals(Constants.CODE_SUCCESS)) {


                            String upi = response.body().getData().getUpiId();


                            SharedPrefHelper.getInstance(MainActivity.this).saveupiId(upi);

                        } else {

                            showToast("Error");


                        }

                    }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<GetUpiResponse> call, Throwable t) {



            }
        });


    }

}