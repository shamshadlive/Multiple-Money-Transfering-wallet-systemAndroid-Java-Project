package com.shamshadlive.parentapplicationv4.retrofit;

import android.text.Editable;

import com.shamshadlive.parentapplicationv4.models.AddWalletBalance;
import com.shamshadlive.parentapplicationv4.models.Childremove;
import com.shamshadlive.parentapplicationv4.models.SendMoney;
import com.shamshadlive.parentapplicationv4.models.WalletBalance;
import com.shamshadlive.parentapplicationv4.models.getchild.GetChildDataResponse;
import com.shamshadlive.parentapplicationv4.models.getchildregistrationresponse.GetChildRegistrationResponse;
import com.shamshadlive.parentapplicationv4.models.getchildwalletbalance.GetChildwalletResponse;
import com.shamshadlive.parentapplicationv4.models.getupi.GetUpiResponse;
import com.shamshadlive.parentapplicationv4.models.getusername.GetUsernameResponse;
import com.shamshadlive.parentapplicationv4.models.login.LoginResponse;
import com.shamshadlive.parentapplicationv4.models.registration.GetRegistrationResponse;
import com.shamshadlive.parentapplicationv4.models.savetransaction.GetTransSaveResponse;
import com.shamshadlive.parentapplicationv4.models.sendmoney.SendMoneyResponse;
import com.shamshadlive.parentapplicationv4.models.transactionsparent.TransactionsResponseParent;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiservices {



    @GET("?r=api/login")
    Call<LoginResponse> loginapitest(@Query("username") String username, @Query("password") String password);


    @GET("?r=api/walletbalance")
    Call<WalletBalance> walletBalance(@Query("username") String username);




    @GET("?r=api/getchildwalletbalance")
    Call<GetChildwalletResponse> getChildwallet(@Query("log_id") String log_id);

    @GET("?r=api/usernameretrive")
    Call<GetUsernameResponse> getusername(@Query("id") String id);

    @GET("?r=api/childremove")
    Call<Childremove> removechild(@Query("log_id") String log_id);

   @GET("?r=api/transactionsparent")
    Call<TransactionsResponseParent> transactionsparent(@Query("log_id") String log_id);




    @GET("?r=api/addwalletbalance")
    Call<AddWalletBalance> addwalletbalance(@Query("username") String username, @Query("rechargemoney") String rechargemoney);

    @GET("?r=api/upiretrive")
    Call<GetUpiResponse> getupidata(@Query("username") String username);


    @GET("?r=api/childretrive")
    Call<GetChildDataResponse> getchilddata(@Query("parent_id") String parent_id);




    @GET("?r=api/addnewchild")
    Call<GetChildRegistrationResponse> getchildregrepdata(@Query("username") String username,
                                                          @Query("password") String password,
                                                          @Query("email") String email,
                                                          @Query("role") String role,
                                                          @Query("status") String status,
                                                          @Query("upiId") String upiId,
                                                          @Query("parent_id") String parent_id,
                                                          @Query("firstname") String firstname,
                                                          @Query("lastname") String lastname,
                                                          @Query("profilepicurl") String profilepicurl,
                                                          @Query("adharnumber") String adharnumber,
                                                          @Query("mobile") String mobile,
                                                          @Query("dateofbirth") String dateofbirth,
                                                          @Query("upiPin") String upiPin




    );


    @GET("?r=api/registration")
    Call<GetRegistrationResponse> getregdata(@Query("username") String username,
                                             @Query("password") String password,
                                             @Query("email") String email,
                                             @Query("role") String role,
                                             @Query("profilepicurl") String profilepicurl,
                                             @Query("noofchild") String noofchild,
                                             @Query("firstname") String firstname,
                                             @Query("adharnumber") String adharnumber,
                                             @Query("lastname") String lastname,
                                             @Query("mobile") String mobile,
                                             @Query("upiPin") String upiPin,
                                             @Query("status") String status,
                                             @Query("upiId") String upiId


    );

    @GET("?r=api/transactionsave")
    Call<GetTransSaveResponse> savetransaction(@Query("sender_upi") String sender_upi,
                                               @Query("receiver_upi") String receiver_upi,
                                               @Query("amount") String amount,
                                               @Query("result") String result,
                                               @Query("timestamp")String timestamp);

    @GET("?r=api/sendmoney")
    Call<SendMoneyResponse> sendmoney(@Query("sender_upi_id") String sender_upi_id, @Query("receiver_upi_id") String receiver_upi_id, @Query("amount") String amount);

}
