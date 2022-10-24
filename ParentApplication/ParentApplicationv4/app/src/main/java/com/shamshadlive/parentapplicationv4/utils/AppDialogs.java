package com.shamshadlive.parentapplicationv4.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.shamshadlive.parentapplicationv4.R;


public class AppDialogs {

    private Context mContext;

    private boolean isExpanded = false;


    private Dialog progressDialog;

    /**
     * to prevent dialogbox showing multiple times
     * */
    private boolean isDialogBoxShowing = false;


    /**
     * constructor
     * */
    public AppDialogs(Context _context){
        mContext = _context;
    }


    /**
     * To show action success dialog
     * @param message
     * @param listener click listener for data passing to main screen
     * */
    public void showSuccessDialog(String message , View.OnClickListener listener){
        try {

            if(isDialogBoxShowing)
            {
                showToast("Dialog already showing");
                return;
            }

            isDialogBoxShowing = true;

            //init the dialog for the alert
            final Dialog dialog = createSimpleDialog(mContext, R.layout.dialog_success, false);

            //avoid closing dialog on clicking outside
            dialog.setCancelable(false);

            //init the submit  button
            final AppCompatButton btn_ok = dialog.findViewById(R.id.buttonOk);
            final AppCompatTextView txt_message = dialog.findViewById(R.id.tv_message);


            txt_message.setText(message);

            //get the dialog window
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //set the layout params for the dialog window
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        isDialogBoxShowing = false;
                        dialog.dismiss();
                        listener.onClick(v);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * show logout confirmation
     * @param listener
     * */
    public void showLogoutWithMessage(String message, OnDualActionButtonClickListener listener){
        try{

            String title = "Logout";
            int icon = R.drawable.ic_baseline_warning_24;

            createDualActionAlertDialogBox(
                    title,
                    message,
                    icon,
                    false,
                    "yes",
                    "No",
                    listener);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * show toast message
     * */
    private void showToast(String message){
        try {

            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

        }catch (Exception e){ }
    }


    /**
     * to show progress bar
     * */
    public void showProgressBar(){
        try{

            //check whether dialog box showing
            if(isDialogBoxShowing)
            {
                showToast("Dialog already showing");
                return;
            }

            if(progressDialog == null) {

                //set the flag true
                isDialogBoxShowing = true;

                //init the dialog for the alert
                progressDialog = createSimpleDialog(mContext, R.layout.dialog_progress, false);

                //avoid closing dialog on clicking outside
                progressDialog.setCancelable(false);

                //get the dialog window
                Window window = progressDialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                //set the layout params for the dialog window
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }else {
                progressDialog.show();
                Log.e("------------","progess showing");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * to show progress bar
     * */
    public void hideProgressbar(){
        try{

            //set the flag false
            isDialogBoxShowing = false;

            if(progressDialog!=null){
                progressDialog.dismiss();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * to create dual action dialog box
     * @param title title dialog box
     * @param message
     * @param alert_icon
     * @param cancellable is cancellable
     * @param yesBtnName positive button name
     * @param noBtnName  negetive button name
     * @param listener action listener
     * */
    private void createDualActionAlertDialogBox(String title,String message, int alert_icon,boolean cancellable,String yesBtnName,String noBtnName,OnDualActionButtonClickListener listener){
        try{

            //check whether dialog box showing
            if(isDialogBoxShowing)
            {
                showToast("Dialog already showing");
                return;
            }

            //set the flag true
            isDialogBoxShowing = true;

            //init the dialog for the alert
            final Dialog dialog = createSimpleDialog(mContext, R.layout.dialog_alert, false);

            //avoid closing dialog on clicking outside
            dialog.setCancelable(cancellable);

            //init the submit  button
            final AppCompatButton btn_yes = dialog.findViewById(R.id.btn_yes);
            final AppCompatButton btn_no = dialog.findViewById(R.id.btn_no);
            final AppCompatTextView txt_message = dialog.findViewById(R.id.tv_message);
            final AppCompatTextView tv_title = dialog.findViewById(R.id.tv_title);
            final ImageView iv_alert_icon = dialog.findViewById(R.id.iv_alert_icon);

            tv_title.setText(title);
            txt_message.setText(message);
            btn_yes.setText(yesBtnName);
            btn_no.setText(noBtnName);
            iv_alert_icon.setImageResource(alert_icon);

            //get the dialog window
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //set the layout params for the dialog window
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            //add click listener on YES button
            btn_yes.setOnClickListener(v -> {
                try {

                    isDialogBoxShowing = false;
                    dialog.dismiss();
                    listener.onClickPositive("yes");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //add click listener on NO button
            btn_no.setOnClickListener(view -> {
                try{

                    isDialogBoxShowing = false;
                    dialog.dismiss();
                    listener.onClickNegetive("no");

                }catch (Exception e){
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    /**
     * to create dual action dialog box
     * @param message
     * @param cancellable is cancellable
     * @param buttonName positive button name
     * @param listener action listener
     * */
    public void createSingleActionAlertDialogBox(String message, boolean cancellable, String buttonName, View.OnClickListener listener){
        try{

            //check whether dialog box showing
            if(isDialogBoxShowing)
            {
                showToast("Dialog already showing");
                return;
            }

            //set the flag true
            isDialogBoxShowing = true;

            //init the dialog for the alert
            final Dialog dialog = createSimpleDialog(mContext, R.layout.dialog_single_action_alert, false);

            //avoid closing dialog on clicking outside
            dialog.setCancelable(cancellable);

            //init the submit  button
            final AppCompatButton action_button = dialog.findViewById(R.id.btn_action);
            final AppCompatTextView txt_message = dialog.findViewById(R.id.tv_message);

            txt_message.setText(message);
            action_button.setText(buttonName);

            //get the dialog window
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //set the layout params for the dialog window
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            //add click listener on YES button
            action_button.setOnClickListener(v -> {
                try {

                    isDialogBoxShowing = false;
                    dialog.dismiss();
                    listener.onClick(v);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createSingleActionSucessDialogBox(String message, boolean cancellable, String buttonName, View.OnClickListener listener){
        try{

            //check whether dialog box showing
            if(isDialogBoxShowing)
            {
                showToast("Dialog already showing");
                return;
            }

            //set the flag true
            isDialogBoxShowing = true;

            //init the dialog for the alert
            final Dialog dialog = createSimpleDialog(mContext, R.layout.dialog_single_action_sucess, false);

            //avoid closing dialog on clicking outside
            dialog.setCancelable(cancellable);

            //init the submit  button
            final AppCompatButton action_button = dialog.findViewById(R.id.btn_action);
            final AppCompatTextView txt_message = dialog.findViewById(R.id.tv_message);

            txt_message.setText(message);
            action_button.setText(buttonName);

            //get the dialog window
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //set the layout params for the dialog window
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            //add click listener on YES button
            action_button.setOnClickListener(v -> {
                try {

                    isDialogBoxShowing = false;
                    dialog.dismiss();
                    listener.onClick(v);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * simple dialog stub
     * */
    private static Dialog createSimpleDialog(Context context, int layout, boolean alignTop) {

        try {
            final Dialog dialog = new Dialog(context);

            /*----- Aligning the dialog in top -----*/
            if (alignTop) {
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.y = 25;
                wlp.gravity = Gravity.TOP;
                window.setAttributes(wlp);
            }

            dialog.setContentView(layout);
            dialog.show();

            return dialog;

        } catch (Exception ex) {

            throw ex;
        }

    }

    public interface OnDualActionButtonClickListener{
        void onClickPositive(String id);
        void onClickNegetive(String id);
    }


}
