package Controller;

import Persistense.Student;
import Service.StudentService;
import Exception.StudentParseException;
import Exception.StudentNotFoundException;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final StudentService studentService;

    public StudentController(String login, String password, String ipAddress, String endPoint) {
        studentService = new StudentService(login, password, ipAddress, endPoint);
    }

    public void start() {
        System.out.println("Выберите действие:");
        System.out.println("1. - получить список студентов по имени");
        System.out.println("2. - получить информацию о студенте по id");
        System.out.println("3. - добавить студента");
        System.out.println("4. - удалить студента по id");
        System.out.println("5. - завершение работы");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            if (scanner.nextLine().equals("1")) {
               try {
                   List<Student> students = studentService.findAll();

                   for (Student student : students) {
                       System.out.println(student);
                   }
               } catch (StudentParseException e) {
                   System.out.println(e.getMessage());
               }

            } else if (scanner.nextLine().equals("2")) {
                try {
                    System.out.println(studentService.findById(Long.parseLong(scanner.nextLine())));
                } catch (StudentNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else if (scanner.nextLine().equals("3")) {
                System.out.println("Введите имя студента");
                studentService.save(scanner.nextLine());

            }  else if (scanner.nextLine().equals("4")) {
                System.out.println("Введите id студента");
                try {
                    studentService.deleteById(scanner.nextLong());
                } catch (StudentNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else if (scanner.nextLine().equals("5")) {
                scanner.close();
                System.out.println("Завершение программы");
                System.exit(1);

            } else {
                System.out.println("Некорректный ввод");
            }
        }

    }
}
