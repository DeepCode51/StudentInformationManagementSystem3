package com.school.dao;

import com.school.pojo.Login;

public interface LoginDao {
    Login login(String username,String password);
    Integer register(String username,String password);
}
