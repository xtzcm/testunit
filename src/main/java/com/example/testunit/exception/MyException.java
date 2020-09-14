package com.example.testunit.exception;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/5 20:30
 */
public class MyException extends Exception {
    private static final long serialVersionUID = 6506403014383746574L;

    private int errCode;
    private String errMsg;

    public MyException(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
