package com.whitefm;

import com.rengwuxian.rxjavasamples.module.cache_6.CacheFragment;
import com.rengwuxian.rxjavasamples.module.elementary_1.ElementaryFragment;
import com.rengwuxian.rxjavasamples.module.map_2.MapFragment;
import com.rengwuxian.rxjavasamples.module.token_4.TokenFragment;
import com.rengwuxian.rxjavasamples.module.token_advanced_5.TokenAdvancedFragment;
import com.rengwuxian.rxjavasamples.module.zip_3.ZipFragment;
import com.whitefm.main.home.FG_HomePage;
import com.whitefm.main.home.FG_Music;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AC_Main extends AppCompatActivity {
    @Bind(android.R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.toolBar) Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);


        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new FG_HomePage();
                    case 1:
                        return new FG_Music();
                    case 2:
                        return new ZipFragment();
                    case 3:
                        return new TokenFragment();
                    case 4:
                        return new TokenAdvancedFragment();
                    case 5:
                        return new CacheFragment();
                    default:
                        return new ElementaryFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.fg_homepage);
                    case 1:
                        return getString(com.whitefm.R.string.fg_dreamer);
                    case 2:
                        return getString(com.whitefm.R.string.fg_stranger_msg);
                    case 3:
                        return getString(com.whitefm.R.string.fg_season_song);
                    case 4:
                        return getString(com.whitefm.R.string.fg_yesterday_msg);
                    case 5:
                        return getString(com.whitefm.R.string.fg_heart_journey);
                    default:
                        return getString(com.whitefm.R.string.fg_lift_msg);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}