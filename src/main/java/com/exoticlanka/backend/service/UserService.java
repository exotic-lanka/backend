package com.exoticlanka.backend.service;

import com.exoticlanka.backend.dto.request.RequestUserDto;

public interface UserService {
    public void signup(RequestUserDto userDto);
    public boolean verifyUser(String type, String token);
}
