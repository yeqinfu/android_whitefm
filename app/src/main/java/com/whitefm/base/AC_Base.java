package com.whitefm.base;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by yeqinfu on 16-4-2.
 */
public abstract class AC_Base extends AC_ToolBar{
    public abstract  int getActivityLayout();
    public abstract  void afterViews();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        afterViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);//绑定注解
    }
}
