package com.shamshadlive.parentapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.shamshadlive.parentapplicationv4.common.Constants;
import com.shamshadlive.parentapplicationv4.models.getupi.GetUpiResponse;
import com.shamshadlive.parentapplicationv4.models.sendmoney.SendMoneyResponse;
import com.shamshadlive.parentapplicationv4.retrofit.Apiservices;
import com.shamshadlive.parentapplicationv4.retrofit.Config;
import com.shamshadlive.parentapplicationv4.utils.SharedPrefHelper;

import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecievemoneyActivity extends AppCompatActivity {

    TextView upiIDparent;

    ImageView idIVQrcode;

    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recievemoney);

        upiIDparent=findViewById(R.id.upiIDparent);

        idIVQrcode=findViewById(R.id.idIVQrcode);

        String username=SharedPrefHelper.getInstance(this).getSavedUsername();

        String upi=SharedPrefHelper.getInstance(this).getSavedupiId();



        if (upi.isEmpty()){

     getupi(username);

        }

    else {

        upiIDparent.setText(upi);
        generate(upi);

        }





      }




    private void generate(String passupi){

        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(passupi, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            idIVQrcode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

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
                           upiIDparent.setText(upi);
                           generate(upi);

                           SharedPrefHelper.getInstance(RecievemoneyActivity.this).saveupiId(upi);

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




public void showToast(String messagepass) {

    Toast.makeText(RecievemoneyActivity.this,
            messagepass, Toast.LENGTH_LONG).show();
}


}