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

import java.util.ArrayList;

public class GatewayAdapter extends RecyclerView.Adapter<GatewayAdapter.ViewHolder>{

    private ArrayList<PaymentGatewayListResponse> paymentGatewayListResponses=new ArrayList<>();
    private Context context;
    String payment_type;


    public GatewayAdapter(Context context, ArrayList<PaymentGatewayListResponse> paymentGatewayListResponses, String payment_type) {
        this.paymentGatewayListResponses=paymentGatewayListResponses;
        this.context=context;
        this.payment_type = payment_type;
    }

    @NonNull
    @Override
    public GatewayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gateway_list_item,viewGroup,false);
        return new GatewayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GatewayAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        viewHolder.gateway_name.setText(paymentGatewayListResponses.get(i).getName());
        viewHolder.gateway_id.setText(Integer.toString(paymentGatewayListResponses.get(i).getId()));

        Picasso.get().load(paymentGatewayListResponses.get(i).getImage()).into(viewHolder.gateway_image);
        viewHolder.select_gateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payment_type == "deposit"){
                    Intent intent = new Intent(context,payment_submit.class);
                    intent.putExtra("gateway_id",paymentGatewayListResponses.get(i).getId());
                    context.startActivity(intent);
                } else if (payment_type.equals("withdraw")){
                    Intent intent = new Intent(context,withdraw_now.class);
                    intent.putExtra("gateway_id",paymentGatewayListResponses.get(i).getId());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentGatewayListResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gateway_image;
        private TextView gateway_name,gateway_id;
        private LinearLayout select_gateway;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gateway_image=(ImageView)itemView.findViewById(R.id.gateway_image);
            gateway_name=(TextView) itemView.findViewById(R.id.gateway_name);
            gateway_id=(TextView)itemView.findViewById(R.id.gateway_desc);
            select_gateway = (LinearLayout) itemView.findViewById(R.id.select_gateway);
        }
    }


}
