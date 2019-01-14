package com.example.beecoder.mvp.presenter;

public interface LoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);
}