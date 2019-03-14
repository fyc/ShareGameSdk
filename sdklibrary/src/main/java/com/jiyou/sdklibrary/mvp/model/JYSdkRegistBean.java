package com.jiyou.sdklibrary.mvp.model;

public class JYSdkRegistBean {

    /**
     * state : 1
     * message :
     * data : {"user_id":"349627","username":"testxQQMDeLJ","nick_name":"testxQQMDeLJ","token":"097bfdfca269e21bbbaccdda929cdfa9","sdk_token":"MzQ5NjI3fHRlc3R4UVFNRGVMSnx0ZXN0eFFRTURlTEp8MTU1MjU1MzE3M3wzNjJmYzNkNDI5ZTVmNzM5OWQxYTRlNWU1MmU5YzAyMw==","id_check":"0","open_id_check":"0","user_type":"1","bind_phone":"0","bind_username":0,"phone":"","close_float":"0"}
     */

    private int state;
    private String message;
    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 349627
         * username : testxQQMDeLJ
         * nick_name : testxQQMDeLJ
         * token : 097bfdfca269e21bbbaccdda929cdfa9
         * sdk_token : MzQ5NjI3fHRlc3R4UVFNRGVMSnx0ZXN0eFFRTURlTEp8MTU1MjU1MzE3M3wzNjJmYzNkNDI5ZTVmNzM5OWQxYTRlNWU1MmU5YzAyMw==
         * id_check : 0
         * open_id_check : 0
         * user_type : 1
         * bind_phone : 0
         * bind_username : 0
         * phone :
         * close_float : 0
         */

        private String user_id;
        private String username;
        private String nick_name;
        private String token;
        private String sdk_token;
        private String id_check;
        private String open_id_check;
        private String user_type;
        private String bind_phone;
        private int bind_username;
        private String phone;
        private String close_float;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSdk_token() {
            return sdk_token;
        }

        public void setSdk_token(String sdk_token) {
            this.sdk_token = sdk_token;
        }

        public String getId_check() {
            return id_check;
        }

        public void setId_check(String id_check) {
            this.id_check = id_check;
        }

        public String getOpen_id_check() {
            return open_id_check;
        }

        public void setOpen_id_check(String open_id_check) {
            this.open_id_check = open_id_check;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getBind_phone() {
            return bind_phone;
        }

        public void setBind_phone(String bind_phone) {
            this.bind_phone = bind_phone;
        }

        public int getBind_username() {
            return bind_username;
        }

        public void setBind_username(int bind_username) {
            this.bind_username = bind_username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getClose_float() {
            return close_float;
        }

        public void setClose_float(String close_float) {
            this.close_float = close_float;
        }
    }
}
