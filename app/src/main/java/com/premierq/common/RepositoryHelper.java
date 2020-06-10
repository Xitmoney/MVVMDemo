package com.premierq.common;

import com.premierq.apiService.PhoneApiServiceTest;
import com.premierq.entity.PhoneEntityTest;
import com.premierqlibrary.common.Utils;
import com.premierqlibrary.net.BaseObserver;
import com.premierqlibrary.net.RetrofitUtils;
import com.premierqlibrary.net.RxSchedulersHelp;

import retrofit2.Call;

/**数据仓库
配置Url与Class注册*/
public class RepositoryHelper {
    private static PhoneApiServiceTest phoneApiServiceTest;

    public static PhoneApiServiceTest getPhoneApiService() {
        if (phoneApiServiceTest == null)
            phoneApiServiceTest = RetrofitUtils.getApiService(PhoneApiServiceTest.class, ServerConfig.BASE_URL);
        return phoneApiServiceTest;
    }




}
 