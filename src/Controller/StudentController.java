package Controller;

import Service.StudentService;

import java.util.Scanner;

public class StudentController {
    private final StudentService studentService;

    public StudentController(String login, String password, String address, String endPoint) {
        studentService = new StudentService(login, password, address, endPoint);
    }

    public void start() {
        System.out.println("Выберите действие:");
        System.out.println("[1] - получить список студентов по имени");
        System.out.println("[2] - получить информацию о студенте по id");
        System.out.println("[3] - добавить студента");
        System.out.println("[4] - удалить студента по id");
        System.out.println("[5] - завершение работы");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    try {
                        studentService.findAll();
                    } catch (StudentParseException e) {
                        e.
                    }


            }
        }

    }
}
