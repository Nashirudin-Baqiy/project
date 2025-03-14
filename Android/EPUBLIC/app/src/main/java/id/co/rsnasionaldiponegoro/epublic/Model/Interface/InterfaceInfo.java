package id.co.rsnasionaldiponegoro.epublic.Model.Interface;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestContact;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRI;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRJ;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDokterJadwal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestEmergency;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHeadline;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHistory;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLayananBantuan;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestListKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestStaff;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestSuaraKonsumen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestUnitStaff;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestVersion;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by xsanz on 9/25/2018.
 */

public interface InterfaceInfo {
    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/info/headline")
    Observable<RestHeadline> getHeadline(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/info/emergency")
    Observable<RestEmergency> getEmergency();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/info/contact")
    Observable<RestContact> getContact();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/info/history")
    Observable<RestHistory> getHistory();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/info/banner")
    Observable<RestInfo> getInfoBanner();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/info/promo")
    Observable<RestInfo> getInfoPromo();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/bunda/fasilitas")
    Observable<RestArtikel> getArtikelParent(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/bunda/fasilitas-list")
    Observable<RestArtikel> getArtikelChildren(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/inbox-user")
    Observable<RestKritikSaran> getKritikSaran(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/add-kritik-saran")
    Observable<RestKritikSaran> addKritikSaran(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @GET("epublic/dokter")
    Observable<RestDokter> getStaffDokter();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @GET("api/kepuasan-pasien")
    Observable<RestSuaraKonsumen> getSuaraKonsumen();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/dokter-jadwal")
    Observable<RestDokterJadwal> getStaffDokterJadwal(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/diary-bunda-rj")
    Observable<RestDiaryBundaRJ> getDiaryBundaRJ(@FieldMap HashMap<String, String> query);


    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/diary-bunda-ri")
    Observable<RestDiaryBundaRI> getDiaryBundaRI(@FieldMap HashMap<String, String> query);


    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/staff")
    Observable<RestUnitStaff>  getUnitStaff(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/staff")
    Observable<RestStaff> getlistStaff(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/layanan-bantuan")
    Observable<RestLayananBantuan> getLayananBantuan();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/antrian-cs2")
    Observable<RestAntrianCS> getAntrianCs();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/antrian-poli")
    Observable<RestAntrianPoli> getAntrianPoli();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/antrian-apotik")
    Observable<RestAntrianApotik> getAntrianApotik();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @POST("epublic/get-version")
    Observable<RestVersion> getVersion();

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("epublic/dokter-by-poli")
    Observable<RestStaff> getlistDokterPoli(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("api/add-keluarga")
    Observable<Rest> addKeluarga(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("api/list-keluarga")
    Observable<RestListKeluarga> listKeluarga(@FieldMap HashMap<String, String> query);

    @Headers("X-API-RS:" + BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("api/del-keluarga")
    Observable<Rest> deleteKeluarga(@FieldMap HashMap<String, String> query);
}
