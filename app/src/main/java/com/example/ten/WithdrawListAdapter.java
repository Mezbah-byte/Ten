package com.example.ten;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.ViewHolder>{
    private ArrayList<WithdrawListResponse> withdrawListResponses=new ArrayList<>();
    private Context context;

    


    public WithdrawListAdapter(Context context, ArrayList<WithdrawListResponse> withdrawListResponses) {
        this.withdrawListResponses=withdrawListResponses;
        this.context=context;
    }

    @NonNull
    @Override
    public WithdrawListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.withdraw_list_item,viewGroup,false);
        return new WithdrawListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WithdrawListAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        viewHolder.withdraw_gateway.setText(withdrawListResponses.get(i).getGateway_type());
        viewHolder.withdraw_date.setText(withdrawListResponses.get(i).getDate());
        viewHolder.withdraw_amount.setText(Integer.toString(withdrawListResponses.get(i).getAmount()));
        viewHolder.receiver_number.setText(withdrawListResponses.get(i).getReceiver_number());

        Picasso.get().load(withdrawListResponses.get(i).getImg()).into(viewHolder.withdraw_status_image);

    }

    @Override
    public int getItemCount() {
        return withdrawListResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView withdraw_status_image;
        private TextView withdraw_gateway,withdraw_date,withdraw_amount,receiver_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            withdraw_status_image=(ImageView)itemView.findViewById(R.id.withdraw_status_image);
            withdraw_gateway=(TextView) itemView.findViewById(R.id.withdraw_gateway);
            withdraw_date=(TextView)itemView.findViewById(R.id.withdraw_date);
            withdraw_amount = (TextView) itemView.findViewById(R.id.withdraw_amount);
            receiver_number = (TextView) itemView.findViewById(R.id.receiver_number);
        }
    }
}
