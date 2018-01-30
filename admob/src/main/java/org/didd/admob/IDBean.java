package org.didd.admob;


import android.support.annotation.Keep;


/**
 * Created by xuewu.jiang@transsnet.com on 2017/11/13.
 */

@Keep
/*public*/ class IDBean {
    /**
     * adId : 12456
     * adLeval : 0
     * appId : 12456     app id
     * id : 0ddfd3d5f27dcc76c115e5ea4fa822c8
     * locationId : 1      location id
     * locationName : des
     * packageName : com
     * typeId : 1
     * typeName : name
     */

    private String adId;
    private int adLeval;
    private String appId;
    private String id;
    private int locationId;
    private String locationName;
    private String packageName;
    private int typeId;
    private String typeName;
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public int getTypeId() {
        return this.typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getPackageName() {
        return this.packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public String getLocationName() {
        return this.locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public int getLocationId() {
        return this.locationId;
    }
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public int getAdLeval() {
        return this.adLeval;
    }
    public void setAdLeval(int adLeval) {
        this.adLeval = adLeval;
    }
    public String getAdId() {
        return this.adId;
    }
    public void setAdId(String adId) {
        this.adId = adId;
    }
    public IDBean(String adId, int adLeval, String appId, String id,
            int locationId, String locationName, String packageName, int typeId,
            String typeName) {
        this.adId = adId;
        this.adLeval = adLeval;
        this.appId = appId;
        this.id = id;
        this.locationId = locationId;
        this.locationName = locationName;
        this.packageName = packageName;
        this.typeId = typeId;
        this.typeName = typeName;
    }
    public IDBean() {
    }

}
