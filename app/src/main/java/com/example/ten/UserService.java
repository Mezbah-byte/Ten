package com.example.ten;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("register")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    @POST("login")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("get_user_data")
    Call<UserResponse> get_user_data(@Body UserdataRequest userdataRequest);

    @POST("get_app_info")
    Call<AppinfoResponse> get_app_info(@Body AppinfoRequest appinfoRequest);

    @POST("ad_request")
    Call<AdResponse> ad_request(@Body AdRequest adRequest);

    @POST("get_payment_method_data")
    Call<PaymentMethodResponse> get_payment_method_data(@Body PaymentdataRequest paymentdataRequest);

    @POST("running_payment_gateway")
    Call<List<PaymentGatewayListResponse>> running_payment_gateway(@Body PaymentGatewayListRequest paymentGatewayListRequest);

    @POST("deposit_gateway_details")
    Call<DepositDetailsResponse> depositDetails(@Body DepositDetailsRequest depositDetailsRequest);

    @POST("submit_deposit_request")
    Call<DepositSubmitResponse> submit_deposit_request(@Body DepositSubmitRequest depositSubmitRequest);


//    @POST("get_exam")
//    Call<ExamResponse> getExam(@Body ExamRequest examRequest);

//    @POST("get_all_exam_status")
//    Call<ExamStatusResponse> getExamStatus(@Body ExamRequest examRequest);

//    @GET("running_exam")
//    Call<ExamsResponse>

}
