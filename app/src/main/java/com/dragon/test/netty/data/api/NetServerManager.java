package com.dragon.test.netty.data.api;

import com.dragon.test.netty.data.constracts.Constant;
import com.dragon.test.netty.data.constracts.NetConstant;
import com.dragondevl.clog.CLog;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetServerManager {

    private static final String TAG = NetServerManager.class.getSimpleName();

    private volatile static NetServerManager sInstance;

    private Retrofit mRFit;

    //private boolean debug;

    private NetServerManager() {
        /*debug = false;
        if (debug) {
            Retrofit.Builder builder = getBuilder(NetConstant.DEV_SERVER_BASE_URL);
            mRFit = builder.build();
        } else {*/
        Retrofit.Builder builder = getBuilder(NetConstant.RELEASE_SERVER_BASE_URL);
        mRFit = builder.build();
        //}
    }

    public static NetServerManager getInstance() {
        if (sInstance == null) {
            synchronized (NetServerManager.class) {
                if (sInstance == null) {
                    sInstance = new NetServerManager();
                }
            }
        }
        return sInstance;
    }

    //拿到实例
    public Retrofit getRFInstance() {
        return mRFit;
    }

    public void changeServerUrl() {
        Retrofit.Builder builder = getBuilder(NetConstant.RELEASE_SERVER_BASE_URL);
        mRFit = builder.build();
    }

    private Retrofit.Builder getBuilder(String url) {
        OkHttpClient netClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(Constant.DEFAULT_NET_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constant.DEFAULT_NET_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Constant.DEFAULT_NET_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                //.addInterceptor(new LogIntercept())
                .build();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(netClient);
    }

    public static class LogIntercept implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            CLog.i(TAG + String.format(Locale.CHINA, "Sending request %s on %s%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            //CLog.i(TAG + String.format(Locale.CHINA, "Receiver response for %s in %.1fms%n%s", response.body().string(), (t2 - t1) / 1e6d, request.headers()));
            return response;
        }

    }

}
