package org.didd.version;

import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * Created by Jiangxuewu on 2018/1/23.
 */
@Keep
public class VersionBean implements Serializable {
    /**
     * alertInterval : 0
     * alertTimes : 0
     * createTime : 2018-01-22 14:15:34
     * dataState : 0
     * downloadUrl : www.baidu.com
     * id : ad699478d5fe3f2c17d7ee48490a9073
     * lowerVersion :
     * netType : 1
     * packageName : com
     * publishVersion : 1
     * startFlag : 0
     * startTime : 2018-01-22 14:15:34
     * stopTime : null
     * strategyName : c
     * updateDesc : adfad
     * upgradeChennel : 1
     * upgradeStrategy : 1
     */

    private int alertInterval;
    private int alertTimes;
    private String createTime;
    private int dataState;
    private String downloadUrl;
    private String id;
    private String lowerVersion;
    private int netType;
    private String packageName;
    private String publishVersion;
    private int startFlag;
    private String startTime;
    private String stopTime;
    private String strategyName;
    private String updateDesc;
    private int upgradeChennel;
    private String upgradeStrategy;

    public VersionBean(int alertInterval, int alertTimes, String createTime,
                       int dataState, String downloadUrl, String id, String lowerVersion,
                       int netType, String packageName, String publishVersion, int startFlag,
                       String startTime, String stopTime, String strategyName,
                       String updateDesc, int upgradeChennel, String upgradeStrategy) {
        this.alertInterval = alertInterval;
        this.alertTimes = alertTimes;
        this.createTime = createTime;
        this.dataState = dataState;
        this.downloadUrl = downloadUrl;
        this.id = id;
        this.lowerVersion = lowerVersion;
        this.netType = netType;
        this.packageName = packageName;
        this.publishVersion = publishVersion;
        this.startFlag = startFlag;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.strategyName = strategyName;
        this.updateDesc = updateDesc;
        this.upgradeChennel = upgradeChennel;
        this.upgradeStrategy = upgradeStrategy;
    }

    public VersionBean() {
    }

    public int getAlertInterval() {
        return alertInterval;
    }

    public void setAlertInterval(int alertInterval) {
        this.alertInterval = alertInterval;
    }

    public int getAlertTimes() {
        return alertTimes;
    }

    public void setAlertTimes(int alertTimes) {
        this.alertTimes = alertTimes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDataState() {
        return dataState;
    }

    public void setDataState(int dataState) {
        this.dataState = dataState;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLowerVersion() {
        return lowerVersion;
    }

    public void setLowerVersion(String lowerVersion) {
        this.lowerVersion = lowerVersion;
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPublishVersion() {
        return publishVersion;
    }

    public void setPublishVersion(String publishVersion) {
        this.publishVersion = publishVersion;
    }

    public int getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(int startFlag) {
        this.startFlag = startFlag;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public int getUpgradeChennel() {
        return upgradeChennel;
    }

    public void setUpgradeChennel(int upgradeChennel) {
        this.upgradeChennel = upgradeChennel;
    }

    public String getUpgradeStrategy() {
        return upgradeStrategy;
    }

    public void setUpgradeStrategy(String upgradeStrategy) {
        this.upgradeStrategy = upgradeStrategy;
    }
}
