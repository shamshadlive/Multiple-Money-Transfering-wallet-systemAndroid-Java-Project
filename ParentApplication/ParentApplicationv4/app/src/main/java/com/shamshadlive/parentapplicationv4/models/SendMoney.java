package com.shamshadlive.parentapplicationv4.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMoney {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("log_id")
        @Expose
        private Integer logId;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public Integer getLogId() {
            return logId;
        }

        public void setLogId(Integer logId) {
            this.logId = logId;
        }

    }




