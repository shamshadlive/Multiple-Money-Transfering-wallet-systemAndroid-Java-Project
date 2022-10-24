
package com.shamshadlive.parentapplicationv4.models.transactionsparent;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TransactionsResponseParent {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("transactions")
    @Expose
    private List<TransactionParent> transactionParents = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TransactionParent> getTransactions() {
        return transactionParents;
    }

    public void setTransactions(List<TransactionParent> transactionParents) {
        this.transactionParents = transactionParents;
    }

}
