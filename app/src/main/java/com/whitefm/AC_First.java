package com.whitefm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.todolist.AC_Drawer;

/**
 * Created by yeqinfu on 9/20/16.
 */
public class AC_First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ac_first);
        afterViews();
    }

    public void afterViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(AC_First.this, AC_Drawer.class);
                startActivity(it);
                finish();
            }
        }, 1000);
    }
}
