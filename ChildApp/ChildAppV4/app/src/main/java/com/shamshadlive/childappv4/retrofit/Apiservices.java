package com.shamshadlive.childappv4.retrofit;


import com.shamshadlive.childappv4.models.login.GetChildLoginResponse;
import com.shamshadlive.childappv4.models.login.WalletBalance;
import com.shamshadlive.childappv4.models.login.getupi.GetUpiResponse;
import com.shamshadlive.childappv4.models.login.savetransaction.GetTransSaveResponse;
import com.shamshadlive.childappv4.models.login.sendmoney.SendMoneyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiservices {



    @GET("?r=api/loginchild")
    Call<GetChildLoginResponse> loginapichild(@Query("username") String username, @Query("password") String password);

    @GET("?r=api/walletbalance")
    Call<WalletBalance> walletBalance(@Query("username") String username);

    @GET("?r=api/upiretrive")
    Call<GetUpiResponse> getupidata(@Query("username") String username);

    @GET("?r=api/sendmoney")
    Call<SendMoneyResponse> sendmoney(@Query("sender_upi_id") String sender_upi_id, @Query("receiver_upi_id") String receiver_upi_id, @Query("amount") String amount);


    @GET("?r=api/transactionsave")
    Call<GetTransSaveResponse> savetransaction(@Query("sender_upi") String sender_upi,
                                               @Query("receiver_upi") String receiver_upi,
                                               @Query("amount") String amount,
                                               @Query("result") String result,
                                               @Query("timestamp")String timestamp);



}
