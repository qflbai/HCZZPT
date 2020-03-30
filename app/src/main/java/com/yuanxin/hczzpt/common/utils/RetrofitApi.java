package com.yuanxin.hczzpt.common.utils;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitApi {
    @Multipart
    @POST
    Observable<Response<ResponseBody>> uplodaOne(@Url String url, @PartMap Map<String, RequestBody> map, @Part MultipartBody.Part var3);
}
