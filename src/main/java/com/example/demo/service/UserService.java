package com.example.demo.service;

import com.example.demo.error.BusinessException;
import com.example.demo.service.model.UserModel;

public interface UserService {

    public UserModel getUserById(Integer id);

    public void TestService(Integer id);

    void register(UserModel userModel)throws BusinessException;



}
