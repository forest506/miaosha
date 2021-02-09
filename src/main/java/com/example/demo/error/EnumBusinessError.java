package com.example.demo.error;

public enum EnumBusinessError implements CommonError{

    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    USER_NOT_EXIST(20001, "用户信息不存在")
    ;

    private int errCode;
    private String errMsg;

    private EnumBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
         this.errMsg = errMsg;
         return this;
    }
}
