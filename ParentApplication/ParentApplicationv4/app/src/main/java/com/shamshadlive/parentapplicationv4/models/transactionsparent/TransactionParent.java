
package com.shamshadlive.parentapplicationv4.models.transactionsparent;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TransactionParent {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_sender")
    @Expose
    private String idSender;
    @SerializedName("id_receiver")
    @Expose
    private String idReceiver;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("sender_upi")
    @Expose
    private String senderUpi;
    @SerializedName("receiver_upi")
    @Expose
    private String receiverUpi;
    @SerializedName("creditordebit")
    @Expose
    private String creditordebit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public String getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(String idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSenderUpi() {
        return senderUpi;
    }

    public void setSenderUpi(String senderUpi) {
        this.senderUpi = senderUpi;
    }

    public String getReceiverUpi() {
        return receiverUpi;
    }

    public void setReceiverUpi(String receiverUpi) {
        this.receiverUpi = receiverUpi;
    }

    public String getCreditordebit() {
        return creditordebit;
    }

    public void setCreditordebit(String creditordebit) {
        this.creditordebit = creditordebit;
    }

}
