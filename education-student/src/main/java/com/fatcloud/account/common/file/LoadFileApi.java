package com.fatcloud.account.common.file;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by 12457 on 2017/8/21.
 */

public interface LoadFileApi {

    @POST
    Call<ResponseBody> loadPdfFile(@Url String fileUrl);

}
