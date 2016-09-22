package com.whitefm.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.orhanobut.logger.Logger;
import com.whitefm.R;

/**
 * Created by yeqinfu on 2016/4/7.
 */
public class AC_ContentFG extends AC_Base {
    Fragment	fragment;
    String						fragmentName;

    public static Intent createIntent(Context context, String fragmentName, String title, Bundle b) {
        Logger.v(fragmentName);
        Intent it = new Intent(context, AC_ContentFG.class);
        it.putExtra("fragmentName", fragmentName);
        it.putExtra("title", title);
        if (b != null) {
            it.putExtras(b);
        }
        return it;
    }

    @Override
    public int getActivityLayout() {
        return R.layout.ac_content_fg;
    }

    @Override
    public void afterViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.ac_content_fg);
        replaceFragment(getIntent());
    }

    private void replaceFragment(Intent intent) {
        try {
            String fragmentName = intent.getStringExtra("fragmentName");
            String title = intent.getStringExtra("title");
            Bundle b = intent.getExtras();
            setFragmentName(fragmentName);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }

            fragment = (Fragment) Class.forName(fragmentName).newInstance();
            if (b != null) {
                fragment.setArguments(b);
            }
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.ll_fragment, fragment);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
