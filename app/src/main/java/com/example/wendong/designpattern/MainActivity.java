package com.example.wendong.designpattern;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wendong.designpattern.bean.UserInfo;

public class MainActivity extends AppCompatActivity {

    public EditText et_Username;
    public EditText et_Password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Username = (EditText) findViewById(R.id.et_Username);
        et_Password = (EditText) findViewById(R.id.et_Password);
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

        boolean b = checkUserInfo(userInfo);
        if (b) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.show();
                    // 模拟网络请求
                    SystemClock.sleep(2000);
                    if (userInfo.username.equals("yang") && userInfo.password.equals("test")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, userInfo.username + "登陆成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, userInfo.username + "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    /**
     * 检查用户信息
     *
     * @param userInfo
     */
    private boolean checkUserInfo(final UserInfo userInfo) {
        progressDialog.show();
        if (!TextUtils.isEmpty(userInfo.username) && !TextUtils.isEmpty(userInfo.password)) {
            return true;
        } else {
            Toast.makeText(this, userInfo.username + "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }
}
