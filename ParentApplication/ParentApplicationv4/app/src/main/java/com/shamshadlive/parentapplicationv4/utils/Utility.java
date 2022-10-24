package com.shamshadlive.parentapplicationv4.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Utility {


    public static void showDatePicker(final EditText et_date_field, Context context){

        try {

            final Calendar newCalendar = Calendar.getInstance();
            final DatePickerDialog datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    try {

                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    String str_date = dayOfMonth+"-"+monthOfYear+"-"+year;
                        et_date_field.setText(str_date);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

            datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

            datePicker.show();

        }catch (Exception e){
            throw e;
        }
    }


}
