package com.admin.core.net;
import com.admin.core.app.ConfigType;
import com.admin.core.app.Latte;

import java.util.WeakHashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Copyright (C)
 *
 * @file: RestCreator
 * @description: Creator：创造者
 */
public class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建 全局Retrofit 客户端
     */
    private static final class RetrofitHolder {
        private static String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST);
        private static Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                //设置网络请求的 url地址
                .baseUrl(BASE_URL)
                //依赖中引入的转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Service 接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    /**
     * @return 返回网络接口请求的实例
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
