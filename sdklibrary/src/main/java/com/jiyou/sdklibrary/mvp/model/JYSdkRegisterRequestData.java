package com.jiyou.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/5.
 * 注册
 */

public class JYSdkRegisterRequestData {

    private String userName;
    private String passWord;

    public JYSdkRegisterRequestData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public JYSdkRegisterRequestData(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    @Override
    public String toString() {
        return "JYSdkRegisterRequestData{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
