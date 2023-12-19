package com.tkmce.pharmacyDelivery.mapper;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.entity.Login;

public class LoginMapper {
    public static LoginDto mapToLoginDto(Login login){
        return new LoginDto(
                login.getId(),
                login.getLogNo(),
                login.getName(),
                login.getPass(),
                login.getEmail()
        );
    }
    public static Login mapToLogin(LoginDto loginDto){
        return new Login(
                loginDto.getId(),
                loginDto.getLogNo(),
                loginDto.getName(),
                loginDto.getPass(),
                loginDto.getEmail()
        );
    }
}
