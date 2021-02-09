package com.example.demo.service.impl;

import com.example.demo.dao.UserDOMapper;
import com.example.demo.dao.UserPasswordDOMapper;
import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.service.UserService;
import com.example.demo.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public void TestService(Integer id){

    }

    @Override
    public UserModel getUserById(Integer id) {

        try {
            UserDO userDO = userDOMapper.selectByPrimaryKey(id);
            if(userDO == null){
                return null;
            }
            UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
            return convertFromDataObject(userDO, userPasswordDO);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){

        if(userDO == null){
            return  null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if(userPasswordDO != null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }

}
