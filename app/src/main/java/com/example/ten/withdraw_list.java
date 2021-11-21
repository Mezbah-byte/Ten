package com.example.ten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class withdraw_list extends AppCompatActivity {


    ArrayList<WithdrawListResponse> withdrawListResponses=new ArrayList<>();
    private WithdrawListAdapter withdrawListAdapter;
    private RecyclerView withdraw_list_recyclerview;
    String un_id;
    String type = "all";
    ExtendedFloatingActionButton withdraw_now_button_onhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_list);
        xml_finder();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        un_id = pref.getString("un_id",null);

        withdraw_list_recyclerview.setLayoutManager(new LinearLayoutManager(this));


        getWithdraw_list(createwithdrawlistRequest());

        withdraw_now_button_onhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(withdraw_list.this,withdraw_now.class));
            }
        });
    }

    public WithdrawListRequest createwithdrawlistRequest(){
        WithdrawListRequest withdrawListRequest = new WithdrawListRequest(un_id,type);
        withdrawListRequest.setUn_id(un_id);
        withdrawListRequest.setType(type);

        return withdrawListRequest;
    }

    public void getWithdraw_list(WithdrawListRequest withdrawListRequest){
        Call<List<WithdrawListResponse>> withdrawListResponsecall = ApiClient.getUserService().withdraw_list(withdrawListRequest);
        withdrawListResponsecall.enqueue(new Callback<List<WithdrawListResponse>>() {
            @Override
            public void onResponse(Call<List<WithdrawListResponse>> call, Response<List<WithdrawListResponse>> response) {
                withdrawListResponses=new ArrayList<>(response.body());
                LinearLayoutManager llm = new LinearLayoutManager(withdraw_list.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                withdrawListAdapter=new WithdrawListAdapter(withdraw_list.this,withdrawListResponses);
                withdraw_list_recyclerview.setAdapter(withdrawListAdapter);
            }

            @Override
            public void onFailure(Call<List<WithdrawListResponse>> call, Throwable t) {
                Toast.makeText(withdraw_list.this,"No internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void xml_finder(){
        withdraw_list_recyclerview = findViewById(R.id.withdraw_list_recyclerview);
        withdraw_now_button_onhome = findViewById(R.id.withdraw_now_button_onwithdrawlist);

    }
}