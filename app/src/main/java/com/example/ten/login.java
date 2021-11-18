package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    Button new_user_sign_up,forget_password,login_btn;
    TextInputEditText login_number,login_password;
    String login_number_data, login_password_data;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        new_user_sign_up = findViewById(R.id.new_user_sign_up);
        forget_password = findViewById(R.id.forgot_password);
        login_btn = findViewById(R.id.login_btn);
        login_number = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        new_user_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,sign_up.class);
                startActivity(i);
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,home.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_number_data= login_number.getText().toString();
                login_password_data = login_password.getText().toString();

                if (login_number_data.isEmpty()){
                    login_number.setError("Phone number is required!");
                    login_number.requestFocus();
                    return;
                }

                if (login_password_data.isEmpty()){
                    login_password.setError("Password is required!");
                    login_password.requestFocus();
                    return;
                }

                loginUser(loginRequest());
            }
        });
    }

    public LoginRequest loginRequest(){
        LoginRequest loginRequest = new LoginRequest(login_number_data,login_password_data);
        loginRequest.setPhone_number(login_number_data);
        loginRequest.setPassword(login_password_data);

        return loginRequest;
    }


    public void loginUser(LoginRequest loginRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().loginUser(loginRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {

                UserResponse userResponse = response.body();

                if (response.isSuccessful()){
                    Toast.makeText(login.this,response.body().toString(),Toast.LENGTH_LONG).show();

//                    assert userResponse != null;

                    if (userResponse.getUn_id() != null){

                        if (userResponse.getResponse_status() == true){
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("un_id", userResponse.getUn_id());
                            editor.putString("name",userResponse.getName());
                            editor.putString("email", userResponse.getEmail());
                            editor.putString("phone_number", userResponse.getphone_number());
                            editor.putString("address", userResponse.getaddress());
                            editor.apply();

                            Intent intent = new Intent(login.this,home.class);
                            startActivity(intent);
                            finish();
                        } else if ( userResponse.getResponse_status() == false) {
                            Toast.makeText(login.this, userResponse.getReason(),Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(login.this, userResponse.getReason(),Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(login.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
//                Toast.makeText(login.this,"Request failed"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}