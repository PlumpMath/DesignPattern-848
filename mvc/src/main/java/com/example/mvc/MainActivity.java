package com.example.mvc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvc.bean.UserInfo;
import com.example.mvc.net.UserLoginNet;

// 将网络、数据库等模型层业务分离出去，MVC
// 存在问题MainActivity存在业务逻辑和界面相关：V少C多
// 拆分出业务逻辑MVP
// 拆分出界面相关MVVM
public class MainActivity extends AppCompatActivity {
    public EditText et_Username;
    public EditText et_Password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        et_Username = (EditText) findViewById(R.id.et_Username);
        et_Password = (EditText) findViewById(R.id.et_Password);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
