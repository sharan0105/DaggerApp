package com.example.daggerpractice;
import com.example.daggerpractice.di.AppComponent;
import com.example.daggerpractice.di.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
public class BaseApplication extends DaggerApplication {
    /*normal scenario when we are extending the application class from Application
    * we create an instance of the component that we want to have the scope throughout the app
    * and then provide a method that we want to return
    * */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return DaggerAppComponent.builder().application(this).build();
        //This returns the appComponent
    }
    /*
    * AppComponent -> throughout the scope of the app
    * MainComp->one part of the app
    * AuthComp-> another comp of the app
    * MainComp and AuthComp do not run parallelly ,one 1 runs another is not running
    * What all we need to do today ,
    * (done)Calculator app -> Works fine , no need to change anything , styling is done proeprly acc to how u styled the app
    * DaggerApp->Big task ,must finish close to half of the videos ,then try and understand retrofit and
    * RxJava -> Design 1 or 2 functions with button clicks using Jack Wharton's library , so that
    * you know how to use views as Observables
    * */
}
