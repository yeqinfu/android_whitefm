package com.todolist;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.whitefm.R;
import com.whitefm.base.AC_Base;

import butterknife.Bind;

/**
 * Created by yeqinfu on 9/22/16.
 */
public class AC_ToDoMain extends AC_Base {
    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Override
    public int getActivityLayout() {
        return R.layout.ac_todo_main;
    }

    @Override
    public void afterViews() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FG_ToDo();
                    case 1:
                        return new FG_Finish();
                    case 2:
                        return new FG_All();
                    default:
                        return new FG_ToDo();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.fg_todo);
                    case 1:
                        return getString(R.string.fg_finish);
                    case 2:
                        return getString(R.string.fg_all);
                    default:
                        return getString(R.string.fg_todo);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}