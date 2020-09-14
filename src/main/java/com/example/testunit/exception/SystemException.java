package com.example.testunit.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @Despriction: 自定义运行时异常
 * @Author: wangcheng
 * @Date: 2018/5/5 21:10
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 4280432478224954955L;

    private Throwable rootCause;
    private Object[] args;
    private String  arg;

    private String code; //错误码
    private String msg;  //错误提示信息


    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message,Object... args) {
        super(message);
        this.args = args;
    }

    public SystemException(String code, String msg) {
        super(code);
        this.arg = msg;
        this.code = code;
        this.msg =  msg;
    }

    public SystemException(ErrCodeEnum errCode) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg();
        this.code = errCode.getError().code();
        this.msg = errCode.getError().msg();
    }

    public SystemException(ErrCodeEnum errCode,Throwable cause) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg();
        this.rootCause = cause;
        this.code = errCode.getError().code();
        this.msg = errCode.getError().msg();
    }

    public SystemException(ErrCodeEnum errCode, String msg) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg() + " " + msg;
        this.code = errCode.getError().code();
        this.msg = errCode.getError().msg();
    }

    public SystemException(Throwable cause) {
        rootCause = cause;
    }

    public SystemException(String message, Throwable cause) {
        super(message);
        rootCause = cause;
    }

    public SystemException(String message, Throwable cause,Object... args) {
        super(message);
        rootCause = cause;
        this.args = args;
    }

    public String getMessage() {
        if (rootCause == null)
            return super.getMessage();
        else
            return super.getMessage() + "; cause exception is: \n\t"
                    + rootCause.toString();
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s) {
        if (rootCause == null) {
            super.printStackTrace(s);
        } else {
            s.println(this);
            rootCause.printStackTrace(s);
        }
    }

    public void printStackTrace(PrintWriter s) {
        if (rootCause == null) {
            super.printStackTrace(s);
        } else {
            s.println(this);
            rootCause.printStackTrace(s);
        }
    }

    public String getArg()
    {
        return arg;
    }
    public void setArg(String arg)
    {
        this.arg = arg;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object[] getArgs() {
        return args;
    }
    public void setArgs(Object[] args) {
        this.args = args;
    }
    public Throwable getRootCause() {
        return rootCause;
    }
}
