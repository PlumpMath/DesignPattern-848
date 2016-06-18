package com.example.databindingdemo.bean;

import android.databinding.ObservableField;

/**
 * Created by wendong on 2016/6/18.
 */
public class TestBean {
    public ObservableField<String> testText;
    public String testEditText;

    public TestBean(ObservableField<String> testText, String testEditText) {
        this.testText = testText;
        this.testEditText = testEditText;
    }
}
