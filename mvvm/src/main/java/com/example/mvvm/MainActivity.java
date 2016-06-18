package com.example.mvvm;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.mvvm.bean.UserInfo;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.event.MyTextWatcher;
import com.example.mvvm.net.UserLoginNet;


// 拆分出界面相关MVVM
public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userInfo = new UserInfo();
        MyTextWatcher textWatcher = new MyTextWatcher(userInfo);
        binding.setMyTextWatcher(textWatcher);

//setContentView(R.layout.activity_main);
    }

    /**
     * 登录按钮
     *
     * @param view
     */
    public void login(View view) {

        boolean b = checkUserInfo(userInfo);
        if (b) {
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserLoginNet userLoginNet = new UserLoginNet();
                    if (userLoginNet.sendLoginUserInfo(userInfo)) {
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
            return false;
        }
    }
}
