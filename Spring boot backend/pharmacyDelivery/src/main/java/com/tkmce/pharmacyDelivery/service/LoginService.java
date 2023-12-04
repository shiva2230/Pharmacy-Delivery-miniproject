package com.tkmce.pharmacyDelivery.service;

import com.tkmce.pharmacyDelivery.dto.LoginDto;

import java.util.List;

public interface LoginService {
    LoginDto createLogin(LoginDto loginDto);
    List<LoginDto> getAllLogins();
}
