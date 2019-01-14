package com.example.beecoder.mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.beecoder.mvp.presenter.LoginPresenter;
import com.example.beecoder.mvp.presenter.LoginPresenterCompl;
import com.example.beecoder.mvp.view.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private EditText EditUser;
    private EditText Editpass;
    private Button ButtonLogin;
    private Button ButtonClear;
    private LoginPresenter loginPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Daftarin dulu id nya
        EditUser = (EditText) findViewById(R.id.login_uname);
        Editpass = (EditText) findViewById(R.id.login_pass);
        ButtonLogin = (Button) findViewById(R.id.Login);
        ButtonClear = (Button) findViewById(R.id.Clear);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);

        //pas diklik
        ButtonLogin.setOnClickListener(this);
        ButtonClear.setOnClickListener(this);

        //init
        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Clear:
                loginPresenter.clear();
                break;
            case R.id.Login:
                loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
                ButtonLogin.setEnabled(false);
                ButtonClear.setEnabled(false);
                loginPresenter.doLogin(EditUser.getText().toString(), Editpass.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText(){
        EditUser.setText("");
        Editpass.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code){
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        ButtonLogin.setEnabled(true);
        ButtonClear.setEnabled(true);
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            Intent home = new Intent(this, Home.class);
            startActivity(home);
            finish();
        } else {
            Toast.makeText(this,"Login Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility){
        progressBar.setVisibility(visibility);
    }

}
