package id.co.rsnasionaldiponegoro.epublic.Model.Interface;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPGen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPLogin;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by xsanz on 9/24/2018.
 */

public interface InterfaceUser {

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/check-account")
    Observable<RestLogin> checkUser(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/add-account")
    Observable<RestLogin> addUser(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/detail-account")
    Observable<RestLogin> detailUser(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/update-account")
    Observable<RestLogin> updateUser(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("api/generate-otp")
    Observable<RestOTPGen> genOTP(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("rsnd-otp")
    Observable<RestOTPGen> genNewOTP(@Field("number") String number);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("api/login-otp")
    Observable<RestOTPLogin> loginOTP(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("verif-otp")
    Observable<RestOTPLogin> loginNewOTP(@Field("number") String nohp, @Field("otp") String pin);
}
