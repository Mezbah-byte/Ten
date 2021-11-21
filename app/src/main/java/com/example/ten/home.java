package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity {

    String un_id;
    CircleImageView profile_img;
    TextView username_onhome,balance_onhome,total_token_onhome,total_withdraw_onhome,phone_number_onhome,email_onhome,address_onhome;
    TextView buy_token_onhome,withdraw_onhome;
    ImageView level_badge_onhome;
    ExtendedFloatingActionButton earn_button_onhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        find_xml();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        un_id = pref.getString("un_id",null);


        getUser(createRequest());
        click_listener();
    }

    public void find_xml(){
        username_onhome = findViewById(R.id.username_onhome);
        balance_onhome = findViewById(R.id.balance_onhome);
        total_token_onhome = findViewById(R.id.total_token_onhome);
        total_withdraw_onhome = findViewById(R.id.total_withdraw_onhome);
        phone_number_onhome = findViewById(R.id.phone_number_onhome);
        email_onhome = findViewById(R.id.email_onhome);
        address_onhome = findViewById(R.id.address_onhome);
        level_badge_onhome = findViewById(R.id.level_badge_onhome);
        earn_button_onhome = findViewById(R.id.earn_button_onhome);
        buy_token_onhome = findViewById(R.id.buy_token_onhome);
        withdraw_onhome = findViewById(R.id.withdraw_onhome);

    }

    public void click_listener(){
        earn_button_onhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAd(Adrequest());
            }
        });

        buy_token_onhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,buy_token.class));
            }
        });

        withdraw_onhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,withdraw_list.class));
            }
        });
    }


    public AdRequest Adrequest(){
        AdRequest adRequest = new AdRequest(un_id,"home");
        adRequest.setUn_id(un_id);
        adRequest.setScreen("home");

        return adRequest;
    }

    public void getAd(AdRequest adRequest){
        Call<AdResponse> adResponseCall = ApiClient.getUserService().ad_request(adRequest);
        adResponseCall.enqueue(new Callback<AdResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<AdResponse> call, @NotNull Response<AdResponse> response) {

                AdResponse adResponse = response.body();

                if (response.isSuccessful()){
                    assert adResponse != null;
                    if (adResponse.getResponse_status()){
                        startActivity(new Intent(home.this,ad_view.class));
                    } else {
                        Toast.makeText(home.this,adResponse.getResponse_reason(),Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(home.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<AdResponse> call, @NotNull Throwable t) {
                Toast.makeText(home.this,"Request failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
                        username_onhome.setText(userResponse.getName());
                        balance_onhome.setText(userResponse.getCurrent_balance());
                        total_token_onhome.setText(Integer.toString(userResponse.getTotal_token()));
                        total_withdraw_onhome.setText(Integer.toString(userResponse.getTotal_withdraw()));
                        phone_number_onhome.setText(userResponse.getPhone_number());
                        email_onhome.setText(userResponse.getEmail());
                        address_onhome.setText(userResponse.getAddress());


                        try {
                            InputStream is = (InputStream) new URL("https://thumbs.dreamstime.com/b/gold-badge-5392868.jpg").getContent();
                            Drawable d = Drawable.createFromStream(is, "src name");
                            level_badge_onhome.setImageDrawable(d);
                        } catch (Exception e) {
                        }

                    } else if(!userResponse.getResponse_status()) {
                        Toast.makeText(home.this, userResponse.getReason(), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(home.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Toast.makeText(home.this,"Request failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}