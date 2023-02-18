package com.gingermadfire.controller;

import com.gingermadfire.exception.StudentNotFoundException;
import com.gingermadfire.persistense.Student;
import com.gingermadfire.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {
    private final StudentService studentService;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public StudentController(String login, String password, String ipAddress, String endPoint) {
        studentService = new StudentService(login, password, ipAddress, endPoint);
    }

    public void start() {
        try {
            this.findMenu();
            String command = reader.readLine();

            while (!command.equals("5")) {

                switch (command) {
                    case "1":
                        this.findAll();
                        break;

                    case "2":
                        this.findById();
                        break;

                    case "3":
                        this.save();
                        break;

                    case "4":
                        this.deleteById();
                        break;

                    default:
                        System.out.println("Некорректный ввод");
                        break;
                }

                this.findMenu();
                command = reader.readLine();
            }
            this.stop();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void findMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. - получить список студентов по имени");
        System.out.println("2. - получить информацию о студенте по id");
        System.out.println("3. - добавить студента");
        System.out.println("4. - удалить студента по id");
        System.out.println("5. - завершение работы");
    }

    private void findAll() {
        try {
            for (Student student : studentService.findAll()) {
                System.out.println(student);
            }

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findById() throws IOException {
        try {
            System.out.println("Введите id студента");
            System.out.println(studentService.findById(Long.parseLong(reader.readLine())));

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Указан неверный формат id");
        }
    }

    private void save() throws IOException {
        System.out.println("Введите имя студента");
        studentService.save(reader.readLine());
    }

    private void deleteById() throws IOException {
        System.out.println("Введите id студента");

        try {
            studentService.deleteById(Long.parseLong(reader.readLine()));

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Указан неверный формат id");
        }
    }

    private void stop() throws IOException {
        reader.close();
        System.out.println("Завершение программы");
        System.exit(1);
    }
}
