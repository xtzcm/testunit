package com.example.testunit.exception;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/5 20:35
 */
public enum ErrCodeEnum {

    @Error(code = "0000", msg = "success")
    success,
    @Error(code = "1000", msg = "系统错误")
    system_err,
    @Error(code = "1001", msg = "请求参数错误")
    param_err;

    /**
     * 返回错误码
     */
    public Error getError() {
        Error error;
        try {
            error = this.getClass().getField(this.name()).getAnnotation(Error.class);
        } catch (Exception e) {
            return null;
        }
        return error;
    }

}
