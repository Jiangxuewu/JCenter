package org.didd.admob;

import android.support.annotation.Keep;

import java.util.List;

/**
 * Created by xuewu.jiang@afmobigroup.com on 2017/11/13.
 */

@Keep
/*public*/ class AdIdBean {
    /**
     * codeDesc : success
     * code : 0
     * data : [{"adId":"12456","adLeval":0,"appId":"12456","id":"0ddfd3d5f27dcc76c115e5ea4fa822c8","locationId":1,"locationName":"locationName","packageName":"com","typeId":1,"typeName":"typeName"}]
     */

    private String codeDesc;
    private int code;
    private List<IDBean> data;

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

    public List<IDBean> getData() {
        return data;
    }

    public void setData(List<IDBean> data) {
        this.data = data;
    }

}
