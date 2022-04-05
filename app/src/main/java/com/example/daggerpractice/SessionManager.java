package com.example.daggerpractice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.daggerpractice.models.User;
import com.example.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager
{
    private static final String TAG = "SessionManager";
    @Inject
    public SessionManager() { }
    private MediatorLiveData<AuthResource<User>> cachedUser=new MediatorLiveData<>();

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if(cachedUser!=null){
            cachedUser.postValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.postValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logout(){
        //to remove the cacheduser object from mediator data//
        Log.d(TAG, "logout: logging out......");
        cachedUser.postValue(AuthResource.<User>logout());
    }


    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }



}
