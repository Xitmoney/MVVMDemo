package com.premierq.common;

import com.premierq.apiService.PhoneApiServiceTest;
import com.premierqlibrary.net.RetrofitUtils;

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
 