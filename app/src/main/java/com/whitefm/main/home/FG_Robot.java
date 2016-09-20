package com.whitefm.main.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.whitefm.R;
import com.whitefm.base.AD_Base;
import com.whitefm.basefm.FG_BaseFM;
import com.whitefm.main.api.API;
import com.whitefm.main.bean.BN_RobotBody;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yeqinfu on 9/19/16.
 */
public class FG_Robot extends FG_BaseFM {
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.bt_send)
    Button btSend;
    @Bind(R.id.lv_list)
    ListView lvList;
    AD_Robot adapter;

    List<BN_Robot> list = new ArrayList<>();


    @Override
    protected int getFgRes() {
        return R.layout.fg_robot;
    }

    @Override
    protected void afterViews() {
        adapter = new AD_Robot(getActivity());
        BN_Robot bn = new BN_Robot();
        bn.setType(0);
        bn.setContent("请输入问题,我回答你");
        list.add(bn);
        adapter.setDatas(list);
        lvList.setAdapter(adapter);
    }

    Observer<BN_RobotBody> observer = new Observer<BN_RobotBody>() {
        @Override
        public void onCompleted() {
            Logger.d("onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            Logger.d("onError");
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(BN_RobotBody body) {
            BN_Robot bn = new BN_Robot();
            bn.setType(0);
            bn.setContent(body.getResult().getContent());
            list.add(bn);
            adapter.setDatas(list);
            lvList.setSelection(lvList.getBottom());

        }
    };

    private void loadContent(String question) {
        subscription = API.getInstance()
                .getAPI("http://api.jisuapi.com/")
                .getAnswer("271d73b1021a5350", question)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @OnClick(R.id.bt_send)
    public void click(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                loadContent(etInput.getText() + "");
                BN_Robot bn = new BN_Robot();
                bn.setType(1);
                bn.setContent(etInput.getText() + "");
                list.add(bn);
                adapter.setDatas(list);
                etInput.setText("");
                lvList.setSelection(lvList.getBottom());
                break;
        }

    }

    class AD_Robot extends AD_Base<BN_Robot> {


        public AD_Robot(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            if (ts.get(position).getType() == 0) {
                v = inflater.inflate(R.layout.item_robot_left, null);
            } else {
                v = inflater.inflate(R.layout.item_robot_right, null);
            }

            TextView tv = (TextView) v.findViewById(R.id.text1);
            tv.setText(ts.get(position).getContent());
            return v;
        }
    }

    class BN_Robot {
        String content;
        int type = 0;//0左边 1右边

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}

