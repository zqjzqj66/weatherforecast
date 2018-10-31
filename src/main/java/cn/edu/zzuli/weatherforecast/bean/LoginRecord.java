package cn.edu.zzuli.weatherforecast.bean;

import java.time.LocalDateTime;

public class LoginRecord {
    //主键ID
    private Integer loginId;

    //记录的时间
    private LocalDateTime recordTime;

    //在一段时间内的登录的次数
    private Integer loginCount;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public LoginRecord(Integer loginId, LocalDateTime recordTime, Integer loginCount) {
        this.loginId = loginId;
        this.recordTime = recordTime;
        this.loginCount = loginCount;
    }

    public LoginRecord() {
    }

    @Override
    public String toString() {
        return "LoginRecord{" +
                "loginId=" + loginId +
                ", recordTime=" + recordTime +
                ", loginCount=" + loginCount +
                '}';
    }
}