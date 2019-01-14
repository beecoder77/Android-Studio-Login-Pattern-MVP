package com.example.beecoder.mvp.presenter;

import android.os.Looper;

import com.example.beecoder.mvp.model.IUser;
import com.example.beecoder.mvp.model.UserModel;
import com.example.beecoder.mvp.view.LoginView;

import android.os.Handler;

public class LoginPresenterCompl implements LoginPresenter {
    LoginView LoginView;
    IUser user;
    Handler handler;

    public LoginPresenterCompl(LoginView LoginView) {
        this.LoginView = LoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        LoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name,passwd);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginView.onLoginResult(result, code);
            }
        }, 5000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity){
        LoginView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser(){
        user = new UserModel("admin","admin");
    }
}