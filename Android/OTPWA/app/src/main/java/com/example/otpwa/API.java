package com.example.otpwa;

import android.text.Editable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("/rsnd-otp")
    Call<OTP> sendOTP(@Field("number") String number);

    @FormUrlEncoded
    @POST("/verif-otp")
    Call<OTP> verifOTP(@Field("number") String number, @Field("otp") Editable otp);
}
