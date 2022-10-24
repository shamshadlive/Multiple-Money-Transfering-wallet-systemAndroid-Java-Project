package com.shamshadlive.parentapplicationv4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;



public class QrcodeScanner extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private boolean isScannerInitialized;


    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout ctl_layout_bottomsheet;
    private AppCompatEditText et_employeeId;
    private AppCompatButton btn_checkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);


        ctl_layout_bottomsheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(ctl_layout_bottomsheet);
        et_employeeId = findViewById(R.id.et_manual_employeeid);
        btn_checkin = findViewById(R.id.btn_manual_checkin);







        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            //initialize qr scanner
            initQRScanner();

        }else {

            isScannerInitialized = false;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 101);
            return;
        }


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                try{

                    switch (newState) {
                        case BottomSheetBehavior.STATE_EXPANDED:


                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:

                            break;
                        default:
                            break;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isScannerInitialized) {
            mCodeScanner.startPreview();
        }


    }


    @Override
    protected void onPause() {

        if (isScannerInitialized) {
            mCodeScanner.releaseResources();
        }

        super.onPause();
    }

    /**
     * initialize QR scanner
     * */
    private void initQRScanner(){
        try {
            //set flag true to identify scanner initialized
            isScannerInitialized = true;
            //get initialize scanner view
            CodeScannerView scannerView = findViewById(R.id.scanner_view);
            mCodeScanner = new CodeScanner(this, scannerView);
            List<BarcodeFormat> scanFormats = new ArrayList<>();
            scanFormats.add(BarcodeFormat.QR_CODE);
            mCodeScanner.setFormats(scanFormats);
            mCodeScanner.setAutoFocusEnabled(true);
            mCodeScanner.setAutoFocusMode(AutoFocusMode.CONTINUOUS);
            mCodeScanner.setTouchFocusEnabled(true);


            //add decode callback
            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                //get scanned data
                                String UpiIDScanned = result.getText();

                                if (!UpiIDScanned.isEmpty()){

                                    if (UpiIDScanned.contains("wallet")){

                                        gotoSendmoney(UpiIDScanned);
                                    }
                                    else
                                    {
                                        showToast("Invalid Upi ID");
                                        gotoSendmoney("");


                                    }

                                }
                                else {
                                    showToast("Empty or error");
                                    gotoSendmoney("");
                                }







                            }catch (Exception e){
                                showToast("error catcch 1");
                                finish();
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

        }catch (Exception e){
            showToast("error catcch 2");
            finish();
            e.printStackTrace();
        }
    }
public void gotoSendmoney(String UpiIDScanned){
    Intent myIntent = new Intent(QrcodeScanner.this, SendMoneyActivity.class);
    myIntent.putExtra("UPI_ID", UpiIDScanned);
    QrcodeScanner.this.startActivity(myIntent);
    QrcodeScanner.this.finish();

}












    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //check request code
        if(requestCode == 101){
            //check permission granted
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //initialize scanner
                initQRScanner();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //release scanner object for garbage collection
        if(isScannerInitialized){
            if(mCodeScanner!=null){
                mCodeScanner = null;
            }
        }
    }

    public void showToast(String message){

        Toast.makeText(QrcodeScanner.this,
                message, Toast.LENGTH_LONG).show();
    }
}
