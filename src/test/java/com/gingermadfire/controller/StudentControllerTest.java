package com.gingermadfire.controller;

import com.gingermadfire.UserLogInData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StudentControllerTest {

    private StudentController studentController;

    @DataProvider(name = "user data")
    public static Object[][] emptyLoginData() {
        return new Object[][]{
                {
                        UserLogInData.LOGIN.getValue(),
                        UserLogInData.PASSWORD.getValue(),
                        UserLogInData.IP_ADDRESS.getValue(),
                        "students"
                }
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "student controller")
    public void emptyConstructorParameterTest() {
        studentController = new StudentController("", "", "", "");
    }

}