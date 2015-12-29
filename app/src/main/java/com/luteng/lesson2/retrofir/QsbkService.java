package com.luteng.lesson2.retrofir;

import com.luteng.lesson2.entity.Item;
import com.luteng.lesson2.entity.Response;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by John on 2015/12/29.
 */
public interface QsbkService {
    //变量用占位符
    @GET("article/list/{type}")
    Call<Response> getList(@Path("type") String type, @Query("page") int page);//此处为get请求，post请求@Field
}
