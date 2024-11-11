package com.school.serviceImp;

import com.school.dao.LoginDao;
import com.school.daoImp.LoginDaoImp;
import com.school.pojo.Login;
import com.school.service.LoginService;

public class LoginServiceImp implements LoginService {
    LoginDao loginDao=new LoginDaoImp();

    @Override
    public Login login(String username, String password) {
        return loginDao.login(username,password);
    }

    @Override
    public Integer register(String username, String password) {
        return loginDao.register(username,password);
    }
}
