package com.example.demo.controller;

import com.example.demo.error.BusinessException;
import com.example.demo.error.EnumBusinessError;
import com.example.demo.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {

        Map<String, Object> responseData = new HashMap<>();
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus("fail");

        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode", businessException.getErrorCode());
            responseData.put("errMsg", businessException.getErrorMsg());
        }else{
            responseData.put("errCode", EnumBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg", EnumBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }

}
