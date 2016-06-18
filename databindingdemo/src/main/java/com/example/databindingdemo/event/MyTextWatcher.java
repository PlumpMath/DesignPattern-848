package com.example.databindingdemo.event;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by wendong on 2016/6/18.
 */
public class MyTextWatcher {
    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("MyTextWatcher", s.toString());
        }
    };
}
