package com.doit_well.customerservice.helper;


import com.doit_well.customerservice.exception.UsersException;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator {

    public static void emailValidation(String email) throws UsersException {
        if (Objects.isNull(email) || email.equals("")){
            throw new UsersException("The email should not be empty");
        }
        if (!emailPatternMatcher(email,"^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new UsersException("The format of the email is not correct");
        }
    }
    public static void lastnameValidation(String lastname) throws UsersException {
        if (Objects.isNull(lastname) || lastname.equals("")){
            throw new UsersException("The lastname should not be empty");
        }
    }
    public static void firstnameValidation(String firstname) throws UsersException {
        if (Objects.isNull(firstname) || firstname.equals("")){
            throw new UsersException("The firstname should not be empty");
        }
    }
    public static void passwordValidation(String password) throws UsersException {
        if(Objects.isNull(password) || password.equals("")){
            throw new UsersException("The password should not be empty");
        }
        if(password.length()<8){
            throw new UsersException("The password should not be less than 8 characters");
        }
    }

    private static Boolean emailPatternMatcher(String email, String regex){
        return Pattern.compile(regex)
                .matcher(email)
                .matches();
    }
}
