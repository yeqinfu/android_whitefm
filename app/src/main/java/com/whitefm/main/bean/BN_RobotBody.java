package com.whitefm.main.bean;

/**
 * Created by yeqinfu on 9/19/16.
 */
public class BN_RobotBody {

    /**
     * status : 0
     * msg : ok
     * result : {"type":"标准回复","content":"杭州今天26℃~32℃ 阵雨转多云 南风3-4级转西南风≤3级\r\n户外活动不适宜在中午前后展开。","relquestion":"查询天气"}
     */

    private String status;
    private String msg;
    /**
     * type : 标准回复
     * content : 杭州今天26℃~32℃ 阵雨转多云 南风3-4级转西南风≤3级
     * 户外活动不适宜在中午前后展开。
     * relquestion : 查询天气
     */

    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String type;
        private String content;
        private String relquestion;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRelquestion() {
            return relquestion;
        }

        public void setRelquestion(String relquestion) {
            this.relquestion = relquestion;
        }
    }
}
