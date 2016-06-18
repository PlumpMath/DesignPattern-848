package com.example.mvvm.event;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.mvvm.bean.UserInfo;

/**
 * Created by wendong on 2016/6/18.
 */
public class MyTextWatcher {

    private UserInfo userInfo;

    public MyTextWatcher(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            userInfo.username = s.toString();
        }
    };

    public TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            userInfo.password = s.toString();
        }
    };
}
