package com.example.demo.error;

public interface CommonError {

    public int getErrorCode();
    public String getErrorMsg();
    public CommonError setErrorMsg(String errMsg);

}
