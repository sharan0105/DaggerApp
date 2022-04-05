package com.example.daggerpractice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerpractice.R;
import com.example.daggerpractice.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    //This will contain that code which is not going to change throughout the course of the app
    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.android_logo);
    }
    /*
    * Basically , methods within a module can have access to not only providers within the same
    * module but also providers in other modules that are part of the component and
    * also those arguments that are binded to the constructor */
    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application,RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }
    @Singleton
    @Provides
    static Drawable provideappDrawable(Application app){
        //this will always return the android logo anywhere in the app
        return ContextCompat.getDrawable(app,R.drawable.android_logo);
    }
    @Singleton //this instance will be created only once in the scope of the app
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
                GsonConverterFactory.create()).build();
    }

}
