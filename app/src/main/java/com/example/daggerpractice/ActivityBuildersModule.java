package com.example.daggerpractice;

import com.example.daggerpractice.di.auth.AuthNetworkModule;
import com.example.daggerpractice.di.auth.AuthViewModelsModule;
import com.example.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule
{
    //This module will contain code for providing all fragments and activities to the component
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthNetworkModule.class}
    )
    //If an instance of network call is needed , it will be provided thru the auth Network module
    //calls specific to the auth sub-comp or the auth activity will be handled by authNetwork module
    abstract AuthActivity contributeAuthActivity();

}
