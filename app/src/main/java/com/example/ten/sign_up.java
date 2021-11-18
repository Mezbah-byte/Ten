package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    Button register,already_user;
    ImageView back_from_sign_up;
    ProgressBar register_progressbar;
    TextInputEditText register_username,register_phone,register_email,register_address,register_password,register_password2;

    String username,phone_number,email,address,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        register = findViewById(R.id.register_btn);
        register_username = findViewById(R.id.register_username);
        register_phone = findViewById(R.id.register_phone);
        register_email = findViewById(R.id.register_email);
        register_address = findViewById(R.id.register_address);
        register_password = findViewById(R.id.register_password);
        register_password2 = findViewById(R.id.register_password2);
//        register_progressbar = findViewById(R.id.RegisterprogressBar);

        back_from_sign_up = findViewById(R.id.back_from_sign_up);
        already_user = findViewById(R.id.already_user_login);

        back_from_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this,login.class));
                finish();
            }
        });


        already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this,login.class));
                finish();
            }
        });



        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        username = register_username.getText().toString();
        phone_number = register_phone.getText().toString();
        email = register_email.getText().toString();
        address = register_address.getText().toString();
        password = register_password.getText().toString();
        String password2 = register_password2.getText().toString();



        if (username.isEmpty()){
            register_username.setError("Full name is required!");
            register_username.requestFocus();
            return;
        }

        if (phone_number.isEmpty()){
            register_phone.setError("Provide a valid phone number");
            register_phone.requestFocus();
            return;
        }

        if (!Patterns.PHONE.matcher(phone_number).matches()){
            register_phone.setError("Provide a valid phone number");
            register_phone.requestFocus();
            return;
        }

        if (email.isEmpty()){
            register_email.setError("Email is required!");
            register_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            register_email.setError("Provide a valid email address.");
            register_email.requestFocus();
            return;
        }

        if (address.isEmpty()){
            register_address.setError("address is required!");
            register_address.requestFocus();
            return;
        }

        if (password.isEmpty()){
            register_password.setError("Password is required!");
            register_password.requestFocus();
            return;
        }

        if (password.length() < 6){
            register_password.setError("Min password should be 6 characters");
            register_password.requestFocus();
            return;
        }


        if (password2.isEmpty()){
            register_password2.setError("Confirm Password is required!");
            register_password2.requestFocus();
            return;
        }


        if (!password.equals(password2)){
            register_password2.setError("Password does not match.");
            register_password2.requestFocus();
            return;
        }

        UserRequest userRequest = new UserRequest(username,email,phone_number,password, address);
        userRequest.setName(username);
        userRequest.setEmail(email);
        userRequest.setphone_number(phone_number);
        userRequest.setPassword(password);
        userRequest.setaddress(address);

        saveUser(createRequest());


    }

    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest(username,email,phone_number,password,address);
        userRequest.setName(username);
        userRequest.setEmail(email);
        userRequest.setphone_number(phone_number);
        userRequest.setPassword(password);
        userRequest.setaddress(address);

        return userRequest;
    }

    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {

                UserResponse userResponse = response.body();

                if (response.isSuccessful()){
//                    Toast.makeText(register.this,response.body().toString(),Toast.LENGTH_LONG).show();

                    String un_id = userResponse.getUn_id();


                    SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("un_id", un_id);
                    editor.putString("name",username);
                    editor.putString("email",email);
                    editor.putString("phone_number",phone_number);
                    editor.putString("address",address);
                    editor.apply();


                    Intent intent = new Intent(sign_up.this,home.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(sign_up.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Toast.makeText(sign_up.this,"Request failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(sign_up.this,login.class));
        finish();

    }
}