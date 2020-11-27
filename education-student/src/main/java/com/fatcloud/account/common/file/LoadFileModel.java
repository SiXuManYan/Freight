package com.fatcloud.account.common.file;

import android.text.TextUtils;


import com.fatcloud.account.frames.network.UrlUtil;
import com.sugar.library.util.CommonUtils;
import com.sugar.library.util.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 12457 on 2017/8/21.
 */

public class LoadFileModel {


    public static void loadPdfFile(String url, Callback<ResponseBody> callback) {


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("X-Requested-With", "XMLHttpRequest")
//                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                                .addHeader("MyToken", CommonUtils.INSTANCE.getShareStudent().getString(Constants.SP_TOKEN, ""))
                                .build();
                        return chain.proceed(request);
                    }
                }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.INSTANCE.getSERVER_HOST())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        LoadFileApi mLoadFileApi = retrofit.create(LoadFileApi.class);
        if (!TextUtils.isEmpty(url)) {
            Call<ResponseBody> call = mLoadFileApi.loadPdfFile(url);
            call.enqueue(callback);
        }

    }
}
