package com.example.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.SessionManager;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.network.auth.AuthApi;
import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authapi;
    private SessionManager sm;
    private MediatorLiveData<AuthResource<User>> authUser=new MediatorLiveData<>();
    @Inject
    public AuthViewModel(AuthApi api,SessionManager sessManager) {
        //this auth api is not provided by the AuthViewModule but by
        //the auth network module and the auth view model can access that
        //coz they are part of the same component
        this.authapi = api;
        this.sm=sessManager;
    }
 //converting the flowable to observable and then using the RxJava calls
/*To follow the MVVM ARCH , there are 2 parts of this ,
we need live data in the viewModel
To convert the flowable object from retrofit to live data
a) convert the flowable to observable(using RxJava)
b)convert the observable to live data
* */
    public void authenticateWithId(int id){
        Log.d(TAG, "authenticateWithId: Attempting to login");
        sm.authenticateWithId(queryUserId(id));
    }
    private LiveData<AuthResource<User>> queryUserId(int id){
        //does the api call from the background thread using RxJava
        return LiveDataReactiveStreams.fromPublisher
                (authapi.getUser(id).
                        onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser= new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        }).map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if(user.getId()==-1)
                        {
                            return AuthResource.error("Authentication Failed",(User)null);
                        }
                        return AuthResource.authenticated(user);
                    }
                }).subscribeOn(Schedulers.io()));
    }
    public LiveData<AuthResource<User>> observeAuthenticationState(){
        return sm.getAuthUser();
    }

}
