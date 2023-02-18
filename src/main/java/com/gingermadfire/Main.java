package com.gingermadfire;

import com.gingermadfire.controller.StudentController;
import com.gingermadfire.exception.NoSuchParameterException;

public class Main {

    public static void main(String[] args) {

        if (args.length > 2) {
            String login = args[0];
            String password = args[1];
            String ipAddress = args[2];
            String endPoint = "students.json";

            StudentController controller = new StudentController(login, password, ipAddress, endPoint);
            controller.start();

        } else {
            throw new NoSuchParameterException("Указано недостаточно входных параметров; необходимо 3");
        }
    }
}