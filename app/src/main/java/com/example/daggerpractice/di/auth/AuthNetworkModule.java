package com.example.daggerpractice.di.auth;

import com.example.daggerpractice.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthNetworkModule {
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        //this retrofit instance is provided by the
        // AppComponent
        return retrofit.create(AuthApi.class);
    }
}
