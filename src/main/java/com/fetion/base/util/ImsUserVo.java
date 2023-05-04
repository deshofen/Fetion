package com.fetion.base.util;
/*这是非加密算法的测试方法*/
public class ImsUserVo {
    private String id;
    private String userName;
    private String password;
    private String nickName;
    private String gender;
    private String avatar;
    private String email;
    private String phone;
    private String sign;
    private String createdTime;
    private String updataTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(String updataTime) {
        this.updataTime = updataTime;
    }

    @Override
    public String toString() {
        return "ImsUserVo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sign='" + sign + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updataTime='" + updataTime + '\'' +
                '}';
    }

//    public void setUserName(String id,String userName, String password, String nickName, String gender, String avatar, String email, String phone, String sign,String  createdTime, String updataTime) {
//
//    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }
}
