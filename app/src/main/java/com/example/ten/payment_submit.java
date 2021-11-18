package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class payment_submit extends AppCompatActivity {

    TextInputEditText sender_number_ondeposit,trx_id_ondeposit;
    Integer payment_type;
    Integer token_quantity;
    Button deposit_submit;
    TextView gateway_type_on_payment_submit,gateway_number_on_payment_submit,total_amount_on_payment_submit,gateway_desc_on_payment_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_submit);

        xml_finder();

        payment_type = getIntent().getExtras().getInt("gateway_id");
        token_quantity = buy_token.token_quantity;


        depositDetails(depositDetailsRequest());

        deposit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depositSubmit(depositSubmitRequest());
            }
        });
    }


    public void xml_finder(){
        sender_number_ondeposit = findViewById(R.id.sender_number_ondeposit);
        trx_id_ondeposit = findViewById(R.id.trx_id_ondeposit);
        gateway_type_on_payment_submit = findViewById(R.id.gateway_type_on_payment_submit);
        gateway_number_on_payment_submit = findViewById(R.id.gateway_number_on_payment_submit);
        total_amount_on_payment_submit = findViewById(R.id.total_amount_on_payment_submit);
        gateway_desc_on_payment_submit = findViewById(R.id.gateway_desc_on_payment_submit);
        deposit_submit = findViewById(R.id.deposit_submit);
    }

    public DepositSubmitRequest depositSubmitRequest(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String un_id = pref.getString("un_id",null);

        String deposit_number = String.valueOf(sender_number_ondeposit.getText());
        String trx = String.valueOf(trx_id_ondeposit.getText());

        DepositSubmitRequest depositSubmitRequest = new DepositSubmitRequest(un_id,deposit_number,trx,token_quantity,payment_type);
        depositSubmitRequest.setUn_id(un_id);
        depositSubmitRequest.setDeposit_number(deposit_number);
        depositSubmitRequest.setTrx(trx);
        depositSubmitRequest.setToken_quantity(token_quantity);
        depositSubmitRequest.setGateway_id(payment_type);

        return depositSubmitRequest;
    }


    public void depositSubmit(DepositSubmitRequest depositSubmitRequest){
        Call<DepositSubmitResponse> depositSubmitResponseCall = ApiClient.getUserService().submit_deposit_request(depositSubmitRequest);
        depositSubmitResponseCall.enqueue(new Callback<DepositSubmitResponse>() {
            @Override
            public void onResponse(@NotNull Call<DepositSubmitResponse> call, @NotNull Response<DepositSubmitResponse> response) {

                DepositSubmitResponse depositSubmitResponse = response.body();

                if (response.isSuccessful()){


                } else {
                    Toast.makeText(payment_submit.this,depositSubmitResponse.response_reason,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DepositSubmitResponse> call, @NotNull Throwable t) {
                Toast.makeText(payment_submit.this,"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
    }


    public DepositDetailsRequest depositDetailsRequest(){
        DepositDetailsRequest depositDetailsRequest = new DepositDetailsRequest(payment_type,token_quantity);
        depositDetailsRequest.setPayment_type(payment_type);
        depositDetailsRequest.setToken_quantity(token_quantity);

        return depositDetailsRequest;
    }


    public void depositDetails(DepositDetailsRequest depositDetailsRequest){
        Call<DepositDetailsResponse> depositDetailsResponseCall = ApiClient.getUserService().depositDetails(depositDetailsRequest);
        depositDetailsResponseCall.enqueue(new Callback<DepositDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<DepositDetailsResponse> call, @NotNull Response<DepositDetailsResponse> response) {

                DepositDetailsResponse depositDetailsResponse = response.body();

                if (response.isSuccessful()){
                    assert depositDetailsResponse != null;
                    gateway_type_on_payment_submit.setText(depositDetailsResponse.getGateway_type_details());
                    gateway_number_on_payment_submit.setText(depositDetailsResponse.getGateway_number());
                    total_amount_on_payment_submit.setText(depositDetailsResponse.getGateway_number());
                    gateway_desc_on_payment_submit.setText(depositDetailsResponse.getGateway_desc());


                    //Toast.makeText(payment_submit.this,depositDetailsResponse.getTotal_price(),Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(payment_submit.this,depositDetailsResponse.response_reason,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DepositDetailsResponse> call, @NotNull Throwable t) {
                Toast.makeText(payment_submit.this,"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
    }
}