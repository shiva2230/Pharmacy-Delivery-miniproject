package com.tkmce.pharmacyDelivery.service.impl;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.entity.Login;
import com.tkmce.pharmacyDelivery.mapper.LoginMapper;
import com.tkmce.pharmacyDelivery.repository.LoginRepository;
import com.tkmce.pharmacyDelivery.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository=loginRepository;
    }
    @Override
    public LoginDto createLogin(LoginDto loginDto) {
        Login login = LoginMapper.mapToLogin(loginDto);
        Login savedLogin = loginRepository.save(login);
        return LoginMapper.mapToLoginDto(savedLogin);
    }

    @Override
    public List<LoginDto> getAllLogins() {
        List<Login> logins=loginRepository.findAll();
        return logins.stream().map((allLogin) -> LoginMapper.mapToLoginDto(allLogin)).collect(Collectors.toList());
    }
}
