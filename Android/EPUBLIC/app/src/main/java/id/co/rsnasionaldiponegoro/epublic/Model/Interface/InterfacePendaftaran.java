package id.co.rsnasionaldiponegoro.epublic.Model.Interface;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by xsanz on 12/27/2018.
 */

public interface InterfacePendaftaran {
    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("login-api")
    Observable<RestPendaftaranLogin> checkUser(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("pasien-api")
    Observable<RestPendaftaranBooking> getPendaftaran(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("getmenu-api")
    Observable<RestPendaftaranPoli> getPoliklinik(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("getdata-api")
    Observable<RestPendaftaranDokter> getDokter(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @GET("get-tanggal")
//    @GET("get-tanggal-vip")
    Observable<RestPendaftaranTanggal> getTanggalDaftar();

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("pend-api")
    Observable<Rest> sendPend(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("booking-batal")
    Observable<Rest> batalPend(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("booking-detail")
    Observable<RestDetailPendaftaran> getPendDetail(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @GET("get-penjamin")
    Observable<RestPenjaminAsuransi> getPenjaminAsuransi();
}
