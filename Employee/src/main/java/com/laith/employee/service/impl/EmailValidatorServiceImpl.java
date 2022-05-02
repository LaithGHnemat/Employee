package com.laith.employee.service.impl;

import com.laith.employee.service.EmailValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class EmailValidatorServiceImpl implements EmailValidatorService {
    private final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public boolean isValidEmail(String email) {
        log.info("Inside the isValidEmail method in EmailValidatorServiceImpl");
        if (email == null) return false;
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
