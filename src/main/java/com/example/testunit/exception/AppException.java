package com.example.testunit.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Despriction: 自定义异常,需要try...catch
 * @Author: wangcheng
 * @Date: 2018/5/5 21:07
 */
public class AppException extends Exception {
    private static final long serialVersionUID = 4795874685730089617L;

    private Throwable rootCause;
    private Object[] args;
    private String  arg;

    private String code; //错误码
    private String msg;  //错误提示信息


    public AppException(String message) {
        super(message);
    }

    public AppException(String message,Object... args) {
        super(message);
        this.args = args;
    }

    public AppException(String code, String msg) {
        super(code);
        this.arg = msg;
        this.code = code;
        this.msg = msg;
    }

    public AppException(ErrCodeEnum errCode) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg();
        this.code = errCode.getError().code();
        this.msg =  errCode.getError().msg();
    }

    public AppException(ErrCodeEnum errCode,Throwable cause) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg();
        this.rootCause = cause;
        this.code = errCode.getError().code();
        this.msg =  errCode.getError().msg();
    }

    public AppException(ErrCodeEnum errCode, String msg) {
        super(errCode.getError().code());
        this.arg = errCode.getError().msg() + " " + msg;
        this.code = errCode.getError().code();
        this.msg =  errCode.getError().msg();
    }

    public AppException(Throwable cause) {
        rootCause = cause;
    }

    public AppException(String message, Throwable cause) {
        super(message);
        rootCause = cause;
    }

    public AppException(String message, Throwable cause,Object... args) {
        super(message);
        rootCause = cause;
        this.args = args;
    }

    /**
     * @param ea  枚举
     */
    @SuppressWarnings("rawtypes")
    public  AppException(Enum ea){
        super(getEnumCode(ea));
        try{
            Annotation annotation = ea.getClass().getField(ea.name()).getAnnotation(Error.class); //在获取注解
            // 在获取想要的方法
            Method method = Error.class.getMethod("msg");
            // 反射调用方法获取相关注解值
            Object msg1 = method.invoke(annotation);
            this.arg = msg1.toString();
            this.code = getEnumCode(ea);
            this.msg = msg1.toString();
        }catch(Exception ex){
            //logger.warn("反射获取错误信息[msg值]异常：",ex);
            ex.printStackTrace();
        }
    }

    /**
     * @param ea  枚举
     * @param cause  异常信息
     */
    @SuppressWarnings("rawtypes")
    public  AppException(Enum ea,Throwable cause){
        super(getEnumCode(ea));
        try{
            Annotation annotation = ea.getClass().getField(ea.name()).getAnnotation(Error.class); //在获取注解
            // 在获取想要的方法
            Method method = Error.class.getMethod("msg");
            // 反射调用方法获取相关注解值
            Object msg1 = method.invoke(annotation);
            this.arg = msg1.toString();
            this.rootCause = cause;
            this.code = getEnumCode(ea);
            this.msg = msg1.toString();
        }catch(Exception ex){
            //logger.warn("反射获取错误信息[msg值]异常：",ex);
            ex.printStackTrace();
        }
    }
    /**
     * @param ea  枚举
     * @param msg 需要追加的错误提示msg
     * @param cause  异常信息
     */
    @SuppressWarnings("rawtypes")
    public  AppException(Enum ea,String msg,Throwable cause){
        super(getEnumCode(ea));
        try{
            Annotation annotation = ea.getClass().getField(ea.name()).getAnnotation(Error.class); //在获取注解
            // 在获取想要的方法
            Method method = Error.class.getMethod("msg");
            // 反射调用方法获取相关注解值
            Object msg1 = method.invoke(annotation);
            this.arg = msg1.toString()+" "+msg;
            this.rootCause = cause;
            this.code = getEnumCode(ea);
            this.msg = msg1.toString()+" "+msg;
        }catch(Exception ex){
            //logger.warn("反射获取错误信息[msg值]异常：",ex);
            ex.printStackTrace();
        }
    }

    /**
     * 给super(message)设值
     * @param ea
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static String getEnumCode(Enum ea){
        try{
            Annotation annotation = ea.getClass().getField(ea.name()).getAnnotation(Error.class); //在获取注解
            // 在获取想要的方法
            Method method = Error.class.getMethod("code");
            // 反射调用方法获取相关注解值
            Object code = method.invoke(annotation);
            return code.toString();
        }catch(Exception ex){
            //logger.warn("反射获取错误[code码]异常：",ex);
            ex.printStackTrace();
        }
        return null;
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
