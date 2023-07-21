package com.example.springbootdemo;

public class IServiceImpl {

    public static IService provideService() {
        return RetrofitConfig.instance().provideRetrofit(null).create(IService.class);
    }
}
