package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.getupi.GetUpiResponse;
import com.shamshadlive.parentapplicationv4.models.savetransaction.GetTransSaveResponse;
import com.shamshadlive.parentapplicationv4.models.sendmoney.SendMoneyResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChildInAddMoneyActivity extends AppCompatActivity {

    EditText edt_rechargeAmount;
    Button btn_topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_in_add_money);


        edt_rechargeAmount=findViewById(R.id.edt_rechargeAmount);
        btn_topup=findViewById(R.id.btn_topup);



        btn_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                childaddmoney();


            }
        });

    }





    public void childaddmoney(){

        String reciverupiid=SharedPrefHelper.getInstance(ChildInAddMoneyActivity.this).getsavechildupiId();
        String sender_upi_id=SharedPrefHelper.getInstance(ChildInAddMoneyActivity.this).getSavedupiId();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);


        String amount=edt_rechargeAmount.getText().toString();

        retrofit2.Call<SendMoneyResponse> call= api.sendmoney(sender_upi_id,reciverupiid,amount);
        call.enqueue(new retrofit2.Callback<SendMoneyResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SendMoneyResponse> call, retrofit2.Response<SendMoneyResponse> response) {



                if (response.isSuccessful()) {

                    if (response.body() != null) {


                        String message = response.body().getMessage();

                        if (message.equals(Constants.CODE_INSUFFICIENT_FUND)) {

                            AppDialogs appDialogs=new AppDialogs(ChildInAddMoneyActivity.this);

                            appDialogs.createSingleActionAlertDialogBox("Alert Insufficent fund", true, "Try Again", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });




                        } else if (message.equals(Constants.CODE_INVALID_RECEIVER)) {

                            showToast("Invalid Reciever");

                        } else if(message.equals(Constants.CODE_SUCCESS)){

                            showToast("Success");

                            String resultTransaction=Constants.SUCESS_CODE1;


                            saveTransactions(sender_upi_id,reciverupiid,amount,resultTransaction);



                            AppDialogs appDialogs=new AppDialogs(ChildInAddMoneyActivity.this);

                            appDialogs.createSingleActionSucessDialogBox("Sucessfully Addedd", true, "Go Back", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    onBackPressed();

//                                    gotochildinterfaceActivity();
                                }
                            });







                        }

                        else {
                            showToast("Unknown Error");
                        }


                    }
                }

            }

            @Override
            public void onFailure(retrofit2.Call<SendMoneyResponse> call, Throwable t) {

                Toast.makeText(ChildInAddMoneyActivity.this,
                        "Unknown Error", Toast.LENGTH_LONG).show();


            }
        });


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

                Toast.makeText(ChildInAddMoneyActivity.this,
                        "Unknown Error", Toast.LENGTH_LONG).show();


            }
        });





    }


    public void gotochildinterfaceActivity(){

        Intent intent = new Intent(ChildInAddMoneyActivity.this, ChildInterfaceActivity.class);
        startActivity(intent);
        ChildInAddMoneyActivity.this.finish();
    }

    public void showToast(String message) {

        Toast.makeText(ChildInAddMoneyActivity.this,
                message, Toast.LENGTH_LONG).show();
    }


}