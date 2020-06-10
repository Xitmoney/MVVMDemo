package com.premierq.apiService;

import com.premierq.entity.PhoneEntityTest;
import com.premierqlibrary.net.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhoneApiServiceTest {

    @GET("/?")
    Observable<BaseResponse<PhoneEntityTest>> getBaseResponsePhone(@Query("app") String app, @Query("phone") String phone, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format);


}
