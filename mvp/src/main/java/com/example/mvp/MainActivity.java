package com.example.mvp;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp.R;
import com.example.mvp.bean.UserInfo;
import com.example.mvp.interf.IUserloginView;
import com.example.mvp.net.UserLoginNet;
import com.example.mvp.presenter.UserLoginPresenter;

public class MainActivity extends AppCompatActivity implements IUserloginView {

    public EditText et_Username;
    public EditText et_Password;
    private ProgressDialog progressDialog;
    private UserLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Username = (EditText) findViewById(R.id.et_Username);
        et_Password = (EditText) findViewById(R.id.et_Password);

        progressDialog = new ProgressDialog(this);
        userLoginPresenter = new UserLoginPresenter(this);
    }

    /**
     * 登录按钮
     *
     * @param view
     */
    public void login(View view) {
        final UserInfo userInfo = new UserInfo();
        userInfo.username = et_Username.getText().toString().trim();
        userInfo.password = et_Password.getText().toString().trim();

        boolean b = userLoginPresenter.checkUserInfo(userInfo);
        if (b) {
            progressDialog.show();
            userLoginPresenter.login(userInfo);
        } else {
            Toast.makeText(this, "用户名或密码为空！", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginFailed(final UserInfo userInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, userInfo.username + "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginSuccess(final UserInfo userInfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, userInfo.username + "登陆成功", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
