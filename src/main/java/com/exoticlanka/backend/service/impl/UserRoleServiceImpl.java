package com.exoticlanka.backend.service.impl;


import com.exoticlanka.backend.entity.UserRole;
import com.exoticlanka.backend.repo.UserRoleRepo;
import com.exoticlanka.backend.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public void initializeRoles() {
        if(userRoleRepo.count()==0){
            if (userRoleRepo.count()==0){
                UserRole admin = new UserRole(1,"ADMIN","admin",null);
                UserRole doc = new UserRole(2,"CUSTOMER","customer",null);
                userRoleRepo.saveAll(List.of(admin,doc));
            }
        }

    }
}
