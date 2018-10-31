package cn.edu.zzuli.weatherforecast.bean;

public class User {
    private Integer userId;

    private String username;

    private String password;

    private String email;

    private Integer userType;

    private String cityId;

    private Integer loginNUm;

    private Integer hourLoginNUm;

    private String  headLOcation;

    private String  signature;

    private Integer userStatus;

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer  getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getLoginNUm() {
        return loginNUm;
    }

    public void setLoginNUm(Integer loginNUm) {
        this.loginNUm = loginNUm;
    }

    public Integer getHourLoginNUm() {
        return hourLoginNUm;
    }

    public void setHourLoginNUm(Integer hourLoginNUm) {
        this.hourLoginNUm = hourLoginNUm;
    }

    public String getHeadLOcation() {
        return headLOcation;
    }

    public void setHeadLOcation(String headLOcation) {
        this.headLOcation = headLOcation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }



    public User() {
    }

    public User(Integer userId, String username, String password, String email, Integer userType, String cityId, Integer loginNUm, Integer hourLoginNUm, String headLOcation, String signature, Integer userStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.cityId = cityId;
        this.loginNUm = loginNUm;
        this.hourLoginNUm = hourLoginNUm;
        this.headLOcation = headLOcation;
        this.signature = signature;
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", cityId='" + cityId + '\'' +
                ", loginNUm=" + loginNUm +
                ", hourLoginNUm=" + hourLoginNUm +
                ", headLOcation='" + headLOcation + '\'' +
                ", signature='" + signature + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}