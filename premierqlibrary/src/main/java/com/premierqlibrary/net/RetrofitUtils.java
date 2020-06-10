package com.premierqlibrary.net;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premierqlibrary.base.IModel;
import com.premierqlibrary.common.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**Retrofit网络层配置*/
public class RetrofitUtils {


    public static <T> T getApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = initRetrofit(baseUrl);
        return retrofit.create(cls);
    }


    /**
     * 初始化Retrofit
     */
    @NonNull
    public static Retrofit initRetrofit(String baseUrl) {
        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return new Retrofit.Builder()
                .client(getOkhttpClientConfig())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * 初始化okhttp
     * 配置OkhttpClient
     * */
    public static OkHttpClient getOkhttpClientConfig(){
        File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        return  new OkHttpClient.Builder()
                .readTimeout(Constans.READ_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(Constans.CONNCET_TIME,TimeUnit.SECONDS)//设置请求超时时
                .writeTimeout(Constans.WRITE_TIME,TimeUnit.SECONDS)//设置写入超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache)
                .build();
             //.addInterceptor(new LogInterceptor())//添加打印拦截器
    }



    public interface Constans{
        int READ_TIME=100;
        int CONNCET_TIME=60;
        int WRITE_TIME=60;
    }
}
