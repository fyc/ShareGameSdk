package com.jiyou.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/5.
 * 登录
 */

public class JYSdkLoginRequestData {

    private String userName;
    private String passWord;

    public JYSdkLoginRequestData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public JYSdkLoginRequestData(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
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

    @Override
    public String toString() {
        return "JYSdkLoginRequestData [userName=" + userName + ", passWord=" + passWord
                + "]";
    }
}
