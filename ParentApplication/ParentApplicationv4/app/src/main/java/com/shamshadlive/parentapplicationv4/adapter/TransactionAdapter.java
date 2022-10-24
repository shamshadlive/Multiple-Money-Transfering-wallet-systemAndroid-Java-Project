package com.shamshadlive.parentapplicationv4.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shamshadlive.parentapplicationv4.R;
import com.shamshadlive.parentapplicationv4.models.getchild.ChildKeyData;
import com.shamshadlive.parentapplicationv4.models.transactionsparent.TransactionParent;
import com.shamshadlive.parentapplicationv4.models.transactionsparent.TransactionsResponseParent;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHOlder>{

    List<TransactionParent> transactionsList;
    OnClickItem listner;


    public TransactionAdapter(List<TransactionParent> gettransactionsparent, OnClickItem getlistner) {

        listner=getlistner;
        transactionsList =gettransactionsparent;



    }

    @NonNull
    @Override
    public TransactionHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.transactionitem,parent,false);

        return new TransactionHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHOlder holder, int position) {


        String receiverUpiID=transactionsList.get(position).getReceiverUpi();
        String senderUpiID=transactionsList.get(position).getSenderUpi();

        String time=transactionsList.get(position).getTimestamp();
        String amount=transactionsList.get(position).getAmount();
        String transactionType=transactionsList.get(position).getCreditordebit();

        if (transactionType.equals("credit")){

   holder.trans_amount.setTextColor(Color.parseColor("#1aa260"));
            holder.trans_amount.setText("+"+amount+"₹");
            holder.trans_upiID.setText(senderUpiID);

        }
        else
        {
            holder.trans_upiID.setText(receiverUpiID);
            holder.trans_amount.setTextColor(Color.RED);
            holder.trans_amount.setText("-"+amount+"₹");
        }

        holder.trans_time.setText(time);









    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public static class TransactionHOlder extends RecyclerView.ViewHolder {

        TextView trans_upiID,trans_time,trans_amount;

        public TransactionHOlder(@NonNull View itemView) {
            super(itemView);


            trans_upiID=itemView.findViewById(R.id.trans_upiID);
            trans_time=itemView.findViewById(R.id.trans_time);
            trans_amount=itemView.findViewById(R.id.trans_amount);



        }
    }

    public interface OnClickItem{

        void onclickitem(TransactionParent Transactions);

    }
}
