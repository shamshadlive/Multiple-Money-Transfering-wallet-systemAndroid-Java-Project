package com.shamshadlive.childappv4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shamshadlive.childappv4.common.Constants;
import com.shamshadlive.childappv4.models.login.getupi.GetUpiResponse;
import com.shamshadlive.childappv4.models.login.savetransaction.GetTransSaveResponse;
import com.shamshadlive.childappv4.models.login.sendmoney.SendMoneyResponse;
import com.shamshadlive.childappv4.retrofit.Apiservices;
import com.shamshadlive.childappv4.retrofit.Config;
import com.shamshadlive.childappv4.utils.SharedPrefHelper;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendMoneyActivity extends AppCompatActivity {

    EditText edt_sendid,edt_sendmoneyamount;
    Button btn_sendmoney;
    ImageView qrcode_scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmoney);


        edt_sendid=findViewById(R.id.edt_sendid);
        edt_sendmoneyamount=findViewById(R.id.edt_sendmoneyamount);
        btn_sendmoney=findViewById(R.id.btn_sendmoney);
        qrcode_scanner=findViewById(R.id.qrcode_scanner);

        String username= SharedPrefHelper.getInstance(this).getSavedUsername();
        String sender_upi_id = SharedPrefHelper.getInstance(SendMoneyActivity.this).getSavedupiId();





        String upiIdScanned = getIntent().getStringExtra("UPI_ID");
        Log.e("upiIdScanned", "onCreate: "+upiIdScanned );

        if(upiIdScanned!=null){

            if (!upiIdScanned.isEmpty())
            {
                edt_sendid.setText(upiIdScanned);

            }
        }







        if (sender_upi_id.isEmpty()){

            getupi(username);
        }


        btn_sendmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                sendmoneyretrofit();

            }
        });


        qrcode_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //edt_sendid.setText("");
               /* edt_sendmoneyamount.setText("");*/
                startQrcodeScanner();

            }
        });





    }

    public void sendmoneyretrofit(){

        String sender_upi_id = SharedPrefHelper.getInstance(SendMoneyActivity.this).getSavedupiId();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);


        String reciverid=edt_sendid.getText().toString();
        String amount=edt_sendmoneyamount.getText().toString();

        retrofit2.Call<SendMoneyResponse> call= api.sendmoney(sender_upi_id,reciverid,amount);
        call.enqueue(new retrofit2.Callback<SendMoneyResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SendMoneyResponse> call, retrofit2.Response<SendMoneyResponse> response) {



                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        String message = response.body().getMessage();

                        if (message.equals(Constants.CODE_INSUFFICIENT_FUND)) {

                            showToast("Insufficient Fund");


                        } else if (message.equals(Constants.CODE_INVALID_RECEIVER)) {

                             showToast("Invalid Reciever");

                        } else if(message.equals(Constants.CODE_SUCCESS)){

                            showToast("Success");

                            String resultTransaction=Constants.SUCESS_CODE1;


                            saveTransactions(sender_upi_id,reciverid,amount,resultTransaction);



                            gotomainActivity();


                        }

                        else {
                            showToast("Unknown Error");
                        }


                    }
                }

            }

            @Override
            public void onFailure(retrofit2.Call<SendMoneyResponse> call, Throwable t) {

                Toast.makeText(SendMoneyActivity.this,
                        "Unknown Error", Toast.LENGTH_LONG).show();


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

                            String sender_upi_id=response.body().getData().getUpiId();

                            SharedPrefHelper.getInstance(SendMoneyActivity.this).saveupiId(sender_upi_id);

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


public void startQrcodeScanner(){

    Intent myIntent = new Intent(SendMoneyActivity.this, QrcodeScanner.class);
    SendMoneyActivity.this.startActivity(myIntent);


}




    public void showToast(String message){

        Toast.makeText(SendMoneyActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

    public void gotomainActivity(){

        Intent intent = new Intent(SendMoneyActivity.this, MainActivity.class);
        startActivity(intent);
        SendMoneyActivity.this.finish();
    }

    public void saveTransactions(String sender_upi,String reciverid,String amount,String resultTransaction){

        showToast("Transaction saved");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timestamp = sdf.format(new Date());

        Log.e("---", "saveTransactions: "+timestamp );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);


        retrofit2.Call<GetTransSaveResponse> call= api.savetransaction(sender_upi,reciverid,amount,resultTransaction,timestamp);
        call.enqueue(new retrofit2.Callback<GetTransSaveResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GetTransSaveResponse> call, retrofit2.Response<GetTransSaveResponse> response) {


                Log.e("---------", "onResponse: "+response );
                Log.e("---------", "onResponse: "+response.body().getMessage() );





            }

            @Override
            public void onFailure(retrofit2.Call<GetTransSaveResponse> call, Throwable t) {

                Toast.makeText(SendMoneyActivity.this,
                        "Unknown Error", Toast.LENGTH_LONG).show();


            }
        });





    }
}