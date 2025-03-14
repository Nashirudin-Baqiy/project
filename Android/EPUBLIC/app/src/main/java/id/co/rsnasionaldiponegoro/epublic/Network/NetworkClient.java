package id.co.rsnasionaldiponegoro.epublic.Network;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xsanz on 9/23/2018.
 */

public class NetworkClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = null;
        if (retrofit == null) {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_API)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientOTP() {
        retrofit = null;
        if (retrofit == null) {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_OTP)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientPendaftaran() {
        retrofit = null;
        if (retrofit == null) {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_API_PEND)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientTele() {
        retrofit = null;
        if (retrofit == null) {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL_API_TELE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }
}
