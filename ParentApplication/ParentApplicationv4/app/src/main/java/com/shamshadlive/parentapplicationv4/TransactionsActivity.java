package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.shamshadlive.parentapplicationv4.adapter.ChildAdapter;
import com.shamshadlive.parentapplicationv4.adapter.TransactionAdapter;
import com.shamshadlive.parentapplicationv4.models.getchild.ChildKeyData;
import com.shamshadlive.parentapplicationv4.models.getchild.GetChildDataResponse;
import com.shamshadlive.parentapplicationv4.models.transactionsparent.TransactionParent;
import com.shamshadlive.parentapplicationv4.models.transactionsparent.TransactionsResponseParent;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionsActivity extends AppCompatActivity {

    RecyclerView transactions_recycle;
    List<TransactionParent> transactionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        transactionsList =new ArrayList<>();
       String logid= SharedPrefHelper.getInstance(TransactionsActivity.this).getsaveLogId();



        Transactionsget(logid);


    }



    public void Transactionsget(String _logid){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiservices api=retrofit.create(Apiservices.class);

        retrofit2.Call<TransactionsResponseParent> call= api.transactionsparent(_logid);
        call.enqueue(new retrofit2.Callback<TransactionsResponseParent>() {
            @Override
            public void onResponse(retrofit2.Call<TransactionsResponseParent> call, retrofit2.Response<TransactionsResponseParent> response) {


                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        TransactionsResponseParent Transactions= response.body();

                        if (Transactions.getTransactions()!=null){

                            transactionsList.clear();
                            transactionsList.addAll(Transactions.getTransactions());

                            transactionsRecycleview();
                            showToast("Transactions Retirved");
                        }





                    }
                }



            }

            @Override
            public void onFailure(retrofit2.Call<TransactionsResponseParent> call, Throwable t) {

                showToast("Failed");
            }
        });



    }



    public void showToast(String message) {

        Toast.makeText(TransactionsActivity.this,
                message, Toast.LENGTH_LONG).show();
    }









    public void transactionsRecycleview(){


        transactions_recycle=findViewById(R.id.transactions_recycle);


        TransactionAdapter transactionAdapter = new TransactionAdapter(transactionsList, new TransactionAdapter.OnClickItem() {
            @Override
            public void onclickitem(TransactionParent Transactions) {

            }
        });




        transactions_recycle.setAdapter(transactionAdapter);


    }

}