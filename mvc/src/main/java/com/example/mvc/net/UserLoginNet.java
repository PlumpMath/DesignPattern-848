package com.example.mvc.net;

import android.os.SystemClock;

import com.example.mvc.bean.UserInfo;

/**
 * Created by wendong on 2016/6/17.
 */
public class UserLoginNet {
    public boolean sendLoginUserInfo(UserInfo userInfo) {
        SystemClock.sleep(2000);
        if (userInfo.username.equals("yang") && userInfo.password.equals("test")) {
            return true;
        }
        return false;
    }
}
