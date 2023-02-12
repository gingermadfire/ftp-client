import Controller.StudentController;

public class Main {

    public static void main(String[] args) {

        String login = args[0];
        String password = args[1];
        String ipAddress = args[2];
        String endPoint = "students";

        StudentController controller = new StudentController(login,password, ipAddress, endPoint);
        controller.start();
    }
}