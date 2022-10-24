package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.models.AddWalletBalance;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.AppDialogs;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMoneyActivity extends AppCompatActivity {

    EditText edt_rechargeAmount;
    Button btn_topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        edt_rechargeAmount=findViewById(R.id.edt_rechargeAmount);
        btn_topup=findViewById(R.id.btn_topup);

        String username = SharedPrefHelper.getInstance(AddMoneyActivity.this).getSavedUsername();

        btn_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rechargemoney = edt_rechargeAmount.getText().toString();


                addmoneyretrofit(username,rechargemoney);

//                Intent intent = new Intent(AddMoneyActivity.this,MainActivity.class);
//
//                startActivity(intent);
//                finish();












            }
        });

    }






    public void addmoneyretrofit(String fusername,String frechargemoney){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);

        retrofit2.Call<AddWalletBalance> call= api.addwalletbalance(fusername,frechargemoney);
        call.enqueue(new retrofit2.Callback<AddWalletBalance>() {
            @Override
            public void onResponse(retrofit2.Call<AddWalletBalance> call, retrofit2.Response<AddWalletBalance> response) {


                AppDialogs appDialogs=new AppDialogs(AddMoneyActivity.this);

                appDialogs.showSuccessDialog(frechargemoney+" Sucessfully Added", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        onBackPressed();
                    }
                });



            }

            @Override
            public void onFailure(retrofit2.Call<AddWalletBalance> call, Throwable t) {

            }
        });

    }

}