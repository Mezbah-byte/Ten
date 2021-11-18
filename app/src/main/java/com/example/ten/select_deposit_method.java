package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class select_deposit_method extends AppCompatActivity {

    ArrayList<PaymentGatewayListResponse> paymentGatewayListResponses=new ArrayList<>();
    private GatewayAdapter gatewayAdapter;
    private RecyclerView gateway_recyclerview;
    String un_id,payment_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_deposit_method);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        un_id = pref.getString("un_id",null);

        payment_type = getIntent().getExtras().getString("payment_type");


        gateway_recyclerview = findViewById(R.id.gateway_recyclerview);
        gateway_recyclerview.setLayoutManager(new LinearLayoutManager(this));


        getPayment_gateway(creategatewayRequest());
    }


    public PaymentGatewayListRequest creategatewayRequest(){
        PaymentGatewayListRequest paymentGatewayListRequest = new PaymentGatewayListRequest(un_id,payment_type);
        paymentGatewayListRequest.setUn_id(un_id);
        paymentGatewayListRequest.setPayment_type(payment_type);

        return paymentGatewayListRequest;
    }

    public void getPayment_gateway(PaymentGatewayListRequest paymentGatewayListRequest){
        Call<List<PaymentGatewayListResponse>> paymentGatewayListResponsecall = ApiClient.getUserService().running_payment_gateway(paymentGatewayListRequest);
        paymentGatewayListResponsecall.enqueue(new Callback<List<PaymentGatewayListResponse>>() {
            @Override
            public void onResponse(Call<List<PaymentGatewayListResponse>> call, Response<List<PaymentGatewayListResponse>> response) {
                paymentGatewayListResponses=new ArrayList<>(response.body());
                gatewayAdapter=new GatewayAdapter(select_deposit_method.this,paymentGatewayListResponses);
                gateway_recyclerview.setAdapter(gatewayAdapter);
            }

            @Override
            public void onFailure(Call<List<PaymentGatewayListResponse>> call, Throwable t) {
                Toast.makeText(select_deposit_method.this,"No internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }
}