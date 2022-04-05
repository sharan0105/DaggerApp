package com.example.daggerpractice.di;
import android.app.Application;

import com.example.daggerpractice.ActivityBuildersModule;
import com.example.daggerpractice.BaseApplication;
import com.example.daggerpractice.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuildersModule.class,AppModule.class,ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {
    //This component will remain throughout the scope of the app as it is defined in the BaseApplication//
    //Extending it tells android to inject base application into the component
    //Basically , the base application wants to use the appComponent  ,therefore, you can think
    //of the base application as the client and the appComponent as the service which the base application wants
    //to use
    SessionManager sm();//this will keep track of the
    //user authentication throughout the app & depending on
    //the state of the authentication we can change the UI not only for a single activity,
    //but for various activities as this is scoped to the lifetime of the app
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application app);
        AppComponent build(); //compulsory method
    }

}
