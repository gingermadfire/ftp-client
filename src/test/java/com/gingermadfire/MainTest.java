package com.gingermadfire;

import com.gingermadfire.exception.NoSuchParameterException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MainTest {

    @DataProvider(name = "no login")
    public static Object[][] emptyLoginData() {
        return new Object[][]{
                {UserLogInData.PASSWORD.getValue(), UserLogInData.IP_ADDRESS.getValue()}
        };
    }

    @DataProvider(name = "no password")
    public static Object[][] emptyPasswordData() {
        return new Object[][]{
                {UserLogInData.LOGIN.getValue(), UserLogInData.IP_ADDRESS.getValue()}
        };
    }

    @DataProvider(name = "no ip-address")
    public static Object[][] emptyIPAddressData() {
        return new Object[][]{
                {UserLogInData.LOGIN.getValue(), UserLogInData.PASSWORD.getValue()}
        };
    }

    @Test(dataProvider = "no login", expectedExceptions = NoSuchParameterException.class, groups = "testing-main")
    public void noLoginInputTest(String password, String ipAddress) {
        Main.main(new String[]{password, ipAddress});
    }

    @Test(dataProvider = "no password", expectedExceptions = NoSuchParameterException.class, groups = "testing main")
    public void noPasswordInputTest(String login, String ipAddress) {
        Main.main(new String[]{login, ipAddress});
    }

    @Test(dataProvider = "no ip-address", expectedExceptions = NoSuchParameterException.class, groups = "testing main")
    public void noIPAddressInputTest(String login, String password) {
        Main.main(new String[]{login, password});
    }
}