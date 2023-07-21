package com.example.springbootdemo;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IService {

    @POST(Constants.REGISTER)
    Single<BaseResponse> doRegister(@Body HashMap<String, String> body);
}
