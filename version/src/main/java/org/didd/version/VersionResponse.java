package org.didd.version;

/**
 * Created by Jiangxuewu on 2018/1/23.
 */

/*public*/ class VersionResponse {


    /**
     * codeDesc : success
     * code : 0
     * data : {"alertInterval":0,"alertTimes":0,"createTime":"2018-01-22 14:15:34","dataState":0,"downloadUrl":"www.baidu.com","id":"ad699478d5fe3f2c17d7ee48490a9073","lowerVersion":"","netType":1,"packageName":"com","publishVersion":"1","startFlag":0,"startTime":"2018-01-22 14:15:34","stopTime":null,"strategyName":"c","updateDesc":"adfad","upgradeChennel":1,"upgradeStrategy":"1"}
     */

    private String codeDesc;
    private int code;
    private VersionBean data;

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public VersionBean getData() {
        return data;
    }

    public void setData(VersionBean data) {
        this.data = data;
    }


}
