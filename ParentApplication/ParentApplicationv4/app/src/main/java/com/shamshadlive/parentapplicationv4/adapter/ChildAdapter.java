package com.shamshadlive.parentapplicationv4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.shamshadlive.parentapplicationv4.R;
import com.shamshadlive.parentapplicationv4.models.getchild.ChildKeyData;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildviewHOlder>{

    List<ChildKeyData> childItemList;
    OnClickItem listner;

    public ChildAdapter(List<ChildKeyData> getchilditemList, OnClickItem getlistner) {

        listner=getlistner;
        childItemList =getchilditemList;
    }

    @NonNull
    @Override
    public ChildviewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.childitem,parent,false);

        return new ChildviewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildviewHOlder holder, int position) {


        holder.tv_childname.setText(childItemList.get(holder.getAdapterPosition()).getFirstname());

        holder.btn_viewchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listner.onclickitem(childItemList.get(holder.getAdapterPosition()));
            }
        });



    }

    @Override
    public int getItemCount() {
        return childItemList.size();
    }

    public static class ChildviewHOlder extends RecyclerView.ViewHolder {

       public TextView tv_childname;
       public Button btn_viewchild;
        public ChildviewHOlder(@NonNull View itemView) {
            super(itemView);

            tv_childname=itemView.findViewById(R.id.childname);
            btn_viewchild=itemView.findViewById(R.id.btn_viewchild);



        }
    }

    public interface OnClickItem{

        void onclickitem(ChildKeyData child);

    }
}
