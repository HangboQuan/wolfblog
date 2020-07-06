package com.tencent.entity;



import java.util.Date;


public class User {
    //用户id
    private Integer userId;
    //用户姓名
    private String userName;
    //用户密码
    private String userPass;
    //用户昵称
    private String userNickName;
    //用户邮箱
    private String userEmail;
    //用户url
    private String userUrl;
    //用户头像
    private String userAvatar;
    //用户最新登录ip
    private String userLastLoginIp;
    //用户注册日期
    private Date userRegisterTime;
    //用户登录日期
    private Date userLastLoginTime;
    //用户状态
    private Integer userStatus;
    //用户文章数
    private Integer articleCount;

    //密码加强
    private String userSalt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public void setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userLastLoginIp='" + userLastLoginIp + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userLastLoginTime=" + userLastLoginTime +
                ", userStatus=" + userStatus +
                ", articleCount=" + articleCount +
                ", userSalt='" + userSalt + '\'' +
                '}';
    }
}
