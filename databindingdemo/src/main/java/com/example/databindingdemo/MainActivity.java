package com.example.databindingdemo;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.databindingdemo.bean.TestBean;
import com.example.databindingdemo.databinding.ContentMainBinding;
import com.example.databindingdemo.event.MyTextWatcher;

public class MainActivity extends AppCompatActivity {
    TestBean testBean;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//editText.addTextChangedListener();
        ContentMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.content_main);
//        android.databinding.DataBindingComponent
//        setContentView(R.layout.activity_main);
        testBean = new TestBean(new ObservableField<>("test"), "EditText");
        dataBinding.setTestBean(testBean);
        MyTextWatcher watcher = new MyTextWatcher();

        dataBinding.setMyTextWatcher(watcher);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
/*        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                testBean.testText.set("success");
            }
        }).start();
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
}
