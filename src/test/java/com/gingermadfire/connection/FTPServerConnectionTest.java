package com.gingermadfire.connection;

import com.gingermadfire.UserLogInData;
import com.gingermadfire.exception.FTPConnectionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FTPServerConnectionTest {
    private final FTPServerConnection FTPConnection = new FTPServerConnection();

    @DataProvider(name = "empty login")
    public static Object[][] emptyLoginData() {
        return new Object[][]{
                {" ", UserLogInData.PASSWORD.getValue(), UserLogInData.IP_ADDRESS.getValue(), "students"}
        };
    }

    @DataProvider(name = "empty password")
    public static Object[][] emptyPasswordData() {
        return new Object[][]{
                {UserLogInData.LOGIN.getValue(), " ", UserLogInData.IP_ADDRESS.getValue(), "students"}
        };
    }

    @DataProvider(name = "empty ip-address")
    public static Object[][] emptyIPAddressData() {
        return new Object[][]{
                {UserLogInData.LOGIN.getValue(), UserLogInData.PASSWORD.getValue(), " ", "students"}
        };
    }

    @Test(
            dataProvider = "empty login",
            expectedExceptions = FTPConnectionException.class,
            groups = "ftp connection"
    )
    public void emptyLoginTest(String login, String password, String ipAddress, String endPoint) {
        FTPConnection.connect(login, password, ipAddress, endPoint);
    }

    @Test(
            dataProvider = "empty password",
            expectedExceptions = FTPConnectionException.class,
            groups = "ftp connection"
    )
    public void emptyPasswordTest(String login, String password, String ipAddress, String endPoint) {
        FTPConnection.connect(login, password, ipAddress, endPoint);
    }

    @Test(
            dataProvider = "empty ip-address",
            expectedExceptions = FTPConnectionException.class,
            groups = "ftp connection"
    )
    public void emptyIPAddressTest(String login, String password, String ipAddress, String endPoint) {
        FTPConnection.connect(login, password, ipAddress, endPoint);
    }
}