package com.gingermadfire;

public enum UserLogInData {

    LOGIN("maxim"),
    PASSWORD("1234"),
    IP_ADDRESS("localhost");

    private final String value;

    UserLogInData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
