package com.whitefm.main.bean;

import com.whitefm.base.BN_BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 8/24/16.
 */
public class BN_HomePageBody extends BN_BaseBody {

    private List<BN_HomePage> resultData;

    public List<BN_HomePage> getResultData() {
        return resultData;
    }

    public void setResultData(List<BN_HomePage> resultData) {
        this.resultData = resultData;
    }
}
