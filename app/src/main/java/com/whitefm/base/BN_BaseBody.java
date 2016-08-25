package com.whitefm.base;

/**
 * Created by yeqinfu on 8/24/16.
 */
public class BN_BaseBody {
    // 返回码，0表示成功，非0表示失败
    private int resultCode;

    // 返回消息，成功为“success”，失败为具体失败信息
    private String resultMessage;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
