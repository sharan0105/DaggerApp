package com.example.daggerpractice.network.auth;

import com.example.daggerpractice.models.User;
import com.google.gson.internal.Excluder;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {
    @GET("users/{id}")
    Flowable<User> getUser(@Path("id")int id);
    //the id written in the get method comes from the value specified
    //in the getUser method . Path annotation tells the get annotation
}
