package id.co.rsnasionaldiponegoro.epublic.Model.Interface;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPoints;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestTMDetailKonsulHistory;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestTopupHistory;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public interface InterfaceTelemedicine {
    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/points")
    Observable<RestPoints> getPoints(@Field("NOHP") String NOHP);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/points/history")
    Observable<RestTopupHistory> getPointsHistory(@Field("NOHP") String NOHP);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/points/topup/req")
    Observable<Rest>reqAddPoints(@FieldMap HashMap<String, String> query);

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
    Observable<RestPendaftaranTanggal> getTanggalDaftar();

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @GET("get-penjamin")
    Observable<RestPenjaminAsuransi> getPenjaminAsuransi();

//    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
//    @GET("get-tanggal-vip")
//    Observable<RestPendaftaranTanggal> getTanggalDaftar();

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("pend-api")
    Observable<Rest> sendPend(@FieldMap HashMap<String, String> query);

//    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
//    @FormUrlEncoded
//    @POST("pend-api-trial")
//    Observable<Rest> sendPend(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("booking-batal")
    Observable<Rest> batalPend(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("booking-detail")
    Observable<RestDetailPendaftaran> getPendDetail(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/konsul/history")
    Observable<RestPendaftaranBooking> getPendaftaranHistory(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/konsul/history/detail")
    Observable<RestTMDetailKonsulHistory> getDetailKonsulHistory(@Field("notrans") String notrans);

    @Headers("X-API-RS:" + BuildConfig.X_API_PEND)
    @FormUrlEncoded
    @POST("api/konsul/checkIn")
    Observable<Rest> checkInTelemedicine(@Field("notrans") String notrans);

    @Multipart
    @POST("api/addInfromConsern")
    Observable<Rest> addInformConsern(@PartMap() HashMap<String, RequestBody> partMap, @Part MultipartBody.Part foto);

}
