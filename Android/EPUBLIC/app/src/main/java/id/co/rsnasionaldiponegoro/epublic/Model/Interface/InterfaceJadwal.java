package id.co.rsnasionaldiponegoro.epublic.Model.Interface;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestJadwalDokter;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by xsanz on 9/26/2018.
 */

public interface InterfaceJadwal {
    @Headers("X-API-RS:"+ BuildConfig.X_API_RS)
    @GET("jadwal-dokter/poliklinik")
    Observable<RestJadwalDokter> getPoliklinik();

    @Headers("X-API-RS:"+ BuildConfig.X_API_RS)
    @FormUrlEncoded
    @POST("jadwal-dokter/jadwal")
    Observable<RestJadwalDokter> getJadwal(@Field("id") String id);
}
