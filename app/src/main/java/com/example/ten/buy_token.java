package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class buy_token extends AppCompatActivity {

    String un_id;
    ImageView plus_onbuytoken,minus_onbuytoken,back_onbuytoken;
    EditText input_token_quantity;
    TextView token_price_onbuytoken,total_token_price_onbuytoken,current_token_onbuytoken;
    Integer token_price,current_token;
    ExtendedFloatingActionButton pay_button_onbuytoken;

    public static Integer token_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_token);
        xml_finder();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        un_id = pref.getString("un_id",null);


        getUser(createRequest());
        click_listener();

        input_token_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.equals("")){
                    set_text();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void click_listener(){
        plus_onbuytoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= input_token_quantity.getText().toString();
                int finalValue=Integer.parseInt(value);

                input_token_quantity.setText(String.valueOf(finalValue+1));
                set_text();

            }
        });

        minus_onbuytoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= input_token_quantity.getText().toString();
                int finalValue=Integer.parseInt(value);


                if(finalValue<2){
                    Toast.makeText(buy_token.this,"Minimum value should be 1!",Toast.LENGTH_LONG).show();
                } else {
                    input_token_quantity.setText(String.valueOf(finalValue-1));
                    set_text();
                }
            }
        });

        pay_button_onbuytoken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value= input_token_quantity.getText().toString();
                int finalValue=Integer.parseInt(value);
                token_quantity = finalValue;


                getPayment_methid(createmethodRequest(finalValue*token_price));

            }
        });
    }


    public void xml_finder(){
        plus_onbuytoken = findViewById(R.id.plus_onbuytoken);
        minus_onbuytoken = findViewById(R.id.minus_onbuytoken);
        input_token_quantity = findViewById(R.id.input_token_number);
        back_onbuytoken = findViewById(R.id.back_onbuytoken);
        token_price_onbuytoken = findViewById(R.id.token_price_onbuytoken);
        total_token_price_onbuytoken = findViewById(R.id.total_token_price_onbuytoken);
        current_token_onbuytoken = findViewById(R.id.current_token_onbuytoken);
        pay_button_onbuytoken = findViewById(R.id.pay_button_onbuytoken);
    }


    public UserdataRequest createRequest(){
        UserdataRequest userdataRequest = new UserdataRequest(un_id);
        userdataRequest.setName(un_id);

        return userdataRequest;
    }

    public void getUser(UserdataRequest userdataRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().get_user_data(userdataRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {

                UserResponse userResponse = response.body();

                if (response.isSuccessful()){
                    assert userResponse != null;
                    if (userResponse.getResponse_status()) {
                        token_price = userResponse.getToken_price();
                        current_token = userResponse.getTotal_token();
                        set_text();

                    } else if(!userResponse.getResponse_status()) {
                        Toast.makeText(buy_token.this, userResponse.getReason(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(buy_token.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Toast.makeText(buy_token.this,"Please check your internet connection !",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void set_text(){
        token_price_onbuytoken.setText(String.valueOf(token_price));
        String value= input_token_quantity.getText().toString();
        int finalValue=Integer.parseInt(value);
        total_token_price_onbuytoken.setText(String.valueOf(finalValue*token_price));
        current_token_onbuytoken.setText(String.valueOf(current_token));
        pay_button_onbuytoken.setText("Pay "+String.valueOf(finalValue*token_price)+" now");
    }


    public PaymentdataRequest createmethodRequest(Integer amount){
        PaymentdataRequest paymentdataRequest = new PaymentdataRequest(un_id,amount);
        paymentdataRequest.setUn_id(un_id);
        paymentdataRequest.setAmount(amount);

        return paymentdataRequest;
    }

    public void getPayment_methid(PaymentdataRequest paymentdataRequest){
        Call<PaymentMethodResponse> paymentMethodResponseCall = ApiClient.getUserService().get_payment_method_data(paymentdataRequest);
        paymentMethodResponseCall.enqueue(new Callback<PaymentMethodResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<PaymentMethodResponse> call, @NotNull Response<PaymentMethodResponse> response) {

                PaymentMethodResponse paymentMethodResponse = response.body();

                if (response.isSuccessful()){
                    assert paymentMethodResponse != null;
                    if (paymentMethodResponse.getResponse_status()) {
                        if (paymentMethodResponse.getPayment_type().equals("us")){
                            Intent i = new Intent(buy_token.this,select_deposit_method.class);
                            i.putExtra("payment_type","deposit");
                            startActivity(i);
                            //startActivity(new Intent(buy_token.this,select_deposit_method.class));
                        } else if (paymentMethodResponse.getPayment_type().equals("ssl")){
                            startActivity(new Intent(buy_token.this,ssl_deposit.class));
                        }
                        //Toast.makeText(buy_token.this,paymentMethodResponse.getPayment_type(),Toast.LENGTH_LONG).show();

                    } else if(!paymentMethodResponse.getResponse_status()) {
                        Toast.makeText(buy_token.this, paymentMethodResponse.getResponse_reason(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(buy_token.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PaymentMethodResponse> call, @NotNull Throwable t) {
                Toast.makeText(buy_token.this,"Please check your internet connection !",Toast.LENGTH_LONG).show();
            }
        });
    }
}