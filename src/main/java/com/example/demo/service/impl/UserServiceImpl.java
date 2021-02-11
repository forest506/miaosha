package com.example.demo.service.impl;

import com.example.demo.dao.UserDOMapper;
import com.example.demo.dao.UserPasswordDOMapper;
import com.example.demo.dataobject.UserDO;
import com.example.demo.dataobject.UserPasswordDO;
import com.example.demo.error.BusinessException;
import com.example.demo.error.EnumBusinessError;
import com.example.demo.service.UserService;
import com.example.demo.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void register(UserModel userModel) throws BusinessException{
        if(userModel == null){
            throw new BusinessException(EnumBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if(StringUtils.isEmpty(userModel.getName()) || userModel.getGender() == null
        || userModel.getAge() == null
        || StringUtils.isEmpty(userModel.getTelphone())){
            throw new BusinessException(EnumBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserDO userDO = convertFromModel(userModel);
        userDOMapper.insertSelective(userDO);

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserDO convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
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
