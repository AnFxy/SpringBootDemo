package com.example.springbootdemo;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private volatile static RetrofitConfig retrofitConfig;

    private RetrofitConfig() {}

    public static RetrofitConfig instance() {
        if (retrofitConfig == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitConfig == null) {
                    retrofitConfig = new RetrofitConfig();
                }
            }
        }
        return retrofitConfig;
    }

    public Retrofit provideRetrofit(@Nullable String url) {
        if (url == null || url.equals("")) {
            url = BuildConfig.BASE_URL;
        }
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(12L, TimeUnit.SECONDS)
                .readTimeout(12L, TimeUnit.SECONDS)
                .writeTimeout(12L, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}
