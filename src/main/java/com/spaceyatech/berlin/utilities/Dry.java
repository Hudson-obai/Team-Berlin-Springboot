package com.spaceyatech.berlin.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Author : sydney mainga
 * Date : 23rd/nov/2023
 * purpose: this class is meant for reuse by other developers contributing to space ya tech project in accordance with Do not Repeat(DRY)
 * Yourself principle
 * */
public class Dry {

    public static final String getCurrentDate() {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        return date;
    }

    public static final String accountCreatedEmail() {

        String accountCreatedEmailString;

        accountCreatedEmailString = ",\nYour account has been created successfully";

        return accountCreatedEmailString;
    }

    public static final String userRegisteredEmail() {

        String userRegisteredEmailString;

        userRegisteredEmailString = ",\nWelcome to SpaceYaTech,You have been registered successfully";

        return userRegisteredEmailString;
    }

    public static final String salutation() {

        String salutationString;

        salutationString = "Dear ";

        return salutationString;
    }
    public static final String  generateOtp(){

        int randomNumber = (int) (Math.random()*9000)+1000;

        String otp  = String.valueOf(randomNumber);

        return otp;
    }




}
