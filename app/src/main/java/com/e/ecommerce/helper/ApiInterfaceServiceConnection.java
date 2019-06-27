package com.e.ecommerce.helper;

import com.e.ecommerce.app.AppConfig;
import com.e.ecommerce.network.ApiInterface;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterfaceServiceConnection {
    public ApiInterface apiServiceMethod() {
        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(AppConfig.CONSUMER_KEY)
                .consumerSecret(AppConfig.CONSUMER_SECRET_KEY)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(oauth1Woocommerce)
                .build();

        ApiInterface apiService = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL_USER).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(ApiInterface.class);
        return apiService;

    }

}