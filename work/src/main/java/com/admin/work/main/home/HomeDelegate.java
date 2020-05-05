package com.admin.work.main.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.admin.core.cache.CacheManagerKt;
import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.net.RestCreator;
import com.admin.core.ui.loader.LatteLoader;
import com.admin.work.R;
import com.admin.work.R2;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.WeakHashMap;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class HomeDelegate extends BottomItemDelegate {


    @BindView(R2.id.home_recyclerview)
    RecyclerView mRecyclerView;

    private HomeRecyclerViewAdapter adapter;

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;

    @BindView(R2.id.delegate_home_weather)
    AppCompatTextView mWeather;

    @BindView(R2.id.delegate_home_weather_image)
    AppCompatImageView mWeatherImage;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        HomeConverter homeConverter = new HomeConverter();

        LatteLoader.showLoading(getContext());
        request(homeConverter);
        initWeather();
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                request(homeConverter);
            }
        });


        rootView.findViewById(R.id.delegate_home_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheManagerKt.save("Jun", new Test());
            }
        });

        rootView.findViewById(R.id.delegate_home_weather_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test jun = (Test) CacheManagerKt.getCache("Jun");
                if (jun != null) {
                    ToastUtils.show(jun.name + "---" + jun.age);
                }
            }
        });

    }

    private void initWeather() {
        WeakHashMap<String, Object> data = new WeakHashMap<>();
        data.put("key", "352c26a1c8e3ad2dd5169c6571f9404f");
        data.put("city", "石家庄");
        RestCreator.getRestService()
                .get("http://apis.juhe.cn/simpleWeather/query?", data)
                .enqueue(new Callback<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        WeatcherBean bean = new Gson().fromJson(result, WeatcherBean.class);
                        if (bean.getResult() != null) {
                            WeatcherBean.ResultBean.RealtimeBean realtime = bean.getResult().getRealtime();
                            mWeather.setText(realtime.getDirect() + "/" + realtime.getTemperature() + "℃");
                            if (realtime.getInfo().contains("晴")) {
                                mWeatherImage.setImageResource(R.drawable.ic_weather_1);
                            } else if (realtime.getInfo().contains("云")) {
                                mWeatherImage.setImageResource(R.drawable.ic_weather_2);
                            } else if (realtime.getInfo().contains("雨")) {
                                mWeatherImage.setImageResource(R.drawable.ic_weather_3);
                            }
                        }
                        LatteLoader.stopLoading();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ToastUtils.show("网络错误");
                        LatteLoader.stopLoading();
                    }
                });
    }

    private void request(HomeConverter homeConverter) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("key", "75b91370009876ca424a865e0007d935");
        map.put("num", 15);
        map.put("word", "河北");
        RestCreator.getRestService()
                .get("http://api.tianapi.com/travel/index", map)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        homeConverter.setJsonData(result);
                        adapter = new HomeRecyclerViewAdapter(homeConverter.convert(), HomeDelegate.this);
                        mRecyclerView.setAdapter(adapter);
                        mRefresh.finishRefresh(true);
                        LatteLoader.stopLoading();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ToastUtils.show("网络错误");
                        LatteLoader.stopLoading();
                    }
                });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }
}
