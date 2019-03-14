package com.jiyou.sdklibrary.mvp.model;

public class JYSdkInitBean {

    /**
     * state : 1
     * message :
     * data : {"is_float_win":0,"is_id_check":"0","is_sh":"0"}
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
         * is_float_win : 0
         * is_id_check : 0
         * is_sh : 0
         */

        private int is_float_win;
        private String is_id_check;
        private String is_sh;

        public int getIs_float_win() {
            return is_float_win;
        }

        public void setIs_float_win(int is_float_win) {
            this.is_float_win = is_float_win;
        }

        public String getIs_id_check() {
            return is_id_check;
        }

        public void setIs_id_check(String is_id_check) {
            this.is_id_check = is_id_check;
        }

        public String getIs_sh() {
            return is_sh;
        }

        public void setIs_sh(String is_sh) {
            this.is_sh = is_sh;
        }
    }
}
