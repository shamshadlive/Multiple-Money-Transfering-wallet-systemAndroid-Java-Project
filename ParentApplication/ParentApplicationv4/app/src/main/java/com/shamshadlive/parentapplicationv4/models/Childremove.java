
package com.shamshadlive.parentapplicationv4.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Childremove {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
