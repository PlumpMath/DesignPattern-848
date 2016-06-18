package com.example.mvp.interf;

import com.example.mvp.bean.UserInfo;

/**
 * Created by wendong on 2016/6/17.
 */
public interface IUserloginView {
    /**
     * 登录成功回调方法
     * @param userInfo
     */
    void loginSuccess(UserInfo userInfo);

    /**
     * 登录失败回调方法
     * @param userInfo
     */
    void loginFailed(UserInfo userInfo);
}
