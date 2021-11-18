package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static int splash=3000;
    private PrefManager prefManager;
    public static final String DATABASE_NAME = "myemployeedatabase";
    SharedPreferences sharedpreferences;

    Animation topanim,bottomanim;
    ImageView image;
    TextView logo;
    String app_version = "1.0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.logo_view);
        logo = findViewById(R.id.logo_text);

        image.setAnimation(topanim);
        logo.setAnimation(bottomanim);

        prefManager = new PrefManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(MainActivity.this,home.class);

                get_app_info(createRequest());

            }
        },splash);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String un_id = pref.getString("un_id",null);

        if (un_id != null){
            startActivity(new Intent(MainActivity.this, home.class));
            finish();
        } else if (un_id == null){
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        }

//        startActivity(new Intent(MainActivity.this, login.class));
//        finish();
    }

    private void launchWelcomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        finish();
    }

    public void cart_back(View view) {
        finish();
    }



    public AppinfoRequest createRequest(){
        AppinfoRequest appinfoRequest = new AppinfoRequest(app_version);
        appinfoRequest.setApp_version(app_version);

        return appinfoRequest;
    }

    public void get_app_info(AppinfoRequest appinfoRequest){
        Call<AppinfoResponse> appinfoResponseCall = ApiClient.getUserService().get_app_info(appinfoRequest);
        appinfoResponseCall.enqueue(new Callback<AppinfoResponse>() {
            @Override
            public void onResponse(@NotNull Call<AppinfoResponse> call, @NotNull Response<AppinfoResponse> response) {

                AppinfoResponse appinfoResponse = response.body();

                if (response.isSuccessful()){
                    assert appinfoResponse != null;
                    if (appinfoResponse.getResponse_status()){
                        if (!prefManager.isFirstTimeLaunch()){
                            launchHomeScreen();
                            finish();
                        } else {
                            launchWelcomeScreen();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, appinfoResponse.getResponse_reason(), Toast.LENGTH_LONG).show();
                    }
//                    assert appinfoResponse != null;
//                    if (appinfoResponse.getCurrent_app_version()==app_version) {
//                        if (!prefManager.isFirstTimeLaunch()) {
//                            launchHomeScreen();
//                            finish();
//                        }else{
//                            launchWelcomeScreen();
//                        }
//
//                    } else {
//                        Toast.makeText(MainActivity.this, "Please Update your app!", Toast.LENGTH_LONG).show();
//                    }

                } else {
                    Toast.makeText(MainActivity.this,"Request failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<AppinfoResponse> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.this,"Please Check your internet connection!",Toast.LENGTH_LONG).show();
            }
        });
    }
}