package com.example.daggerpractice.ui.auth;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice.R;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

    public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel; //this will be provided by the AuthViewModels Module
    private EditText userId;
    private ProgressBar progressBar;
    @Inject
    ViewModelProviderFactory factory;//this will be made/provided by the app component //
    @Inject
    Drawable logo;
    @Inject
    RequestManager glideInstance;
    //When authActivity will be made , the appModule will inject these fields//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userId=findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);

        progressBar=findViewById(R.id.progress_bar);

        viewModel= ViewModelProviders.of(this,factory).get(AuthViewModel.class);

        glideInstance.load(logo).into((ImageView) findViewById(R.id.login_logo));

        subscribeObservers();
        }
        private void subscribeObservers(){
        viewModel.observeAuthenticationState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    switch(userAuthResource.status){
                        case LOADING:{showProgressBar(true);break;}
                        case AUTHENTICATED:{
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: "+userAuthResource.data.getEmail());
                            break;
                        }
                        case ERROR:{
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this,userAuthResource.message+" " +
                                            "\n Did you enter a number b/w 1 and 10",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{showProgressBar(false);break;}
                    }
                }
            }
        });

        }
        private void showProgressBar(boolean isVisible) {
            if (isVisible) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
        private void login()
        {
            if(TextUtils.isEmpty(userId.getText().toString())){
                return;
            }
            viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
        }
        @Override
        public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button:{
                login();
                break;
            }
        }
        }
    }