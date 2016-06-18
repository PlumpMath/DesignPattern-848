package com.example.mvp.presenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.mvp.MainActivity;
import com.example.mvp.bean.UserInfo;
import com.example.mvp.interf.IUserloginView;
import com.example.mvp.net.UserLoginNet;

/**
 * Created by wendong on 2016/6/17.
 */
public class UserLoginPresenter {

    private final IUserloginView mainView;

    public UserLoginPresenter(IUserloginView mainView) {
        this.mainView = mainView;
    }

    /**
     * 登录
     * @param userInfo
     */
    public void login(final UserInfo userInfo) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserLoginNet userLoginNet = new UserLoginNet();
                    if (userLoginNet.sendLoginUserInfo(userInfo)) {
                        mainView.loginSuccess(userInfo);
                    } else {
                        mainView.loginFailed(userInfo);
                    }
                }
            }).start();
    }

    /**
     * 检查用户信息
     *
     * @param userInfo
     */
    public boolean checkUserInfo(final UserInfo userInfo) {
        if (!TextUtils.isEmpty(userInfo.username) && !TextUtils.isEmpty(userInfo.password)) {
            return true;
        }
        return false;
    }
}
