package com.school.service;

import com.school.pojo.Login;

public interface LoginService {
    Login login(String username, String password);
    Integer register(String username,String password);
}
