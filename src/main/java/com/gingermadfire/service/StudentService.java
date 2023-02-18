package com.gingermadfire.service;

import com.gingermadfire.connection.FTPServerConnection;
import com.gingermadfire.exception.EmptyFileException;
import com.gingermadfire.exception.StudentNotFoundException;
import com.gingermadfire.parser.JSONParser;
import com.gingermadfire.persistense.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class StudentService {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public StudentService(String login, String password, String ipAddress, String endPoint) {
        FTPServerConnection connection = new FTPServerConnection();
        connection.connect(login, password, ipAddress, endPoint);
        try {
            InputStream inputStream = connection.findInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            students = new JSONParser().parse(bufferedReader.lines().reduce("", (result, line) -> result + line));
            if (students == null || students.isEmpty()) {
                throw new EmptyFileException("Был передан пустой файл");
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findAll() throws StudentNotFoundException {
        if (!students.isEmpty()) {
            students.sort(Comparator.comparing(Student::getName));

            return students;
        } else {
            throw new StudentNotFoundException("Не удалось найти ни одного пользователя");
        }
    }

    public Student findById(long id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        throw new StudentNotFoundException(String.format("Пользователь с данным id: %d не найден.", id));
    }

    public void save(String name) {
        long[] studentIds = new long[students.size()];
        students.sort(Comparator.comparing(Student::getId));

        for (int i = 0; i < studentIds.length; i++) {
            studentIds[i] = students.get(i).getId();
        }

        for (long i = 0; i < studentIds.length; i++) {
            if (studentIds[(int) i] != i + 1) {
                students.add(new Student(i + 1, name));
                return;
            }
        }

        long id = studentIds[studentIds.length - 1];
        students.add(new Student(id + 1, name));
    }


    public void deleteById(long id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                return;
            }
        }

        throw new StudentNotFoundException(String.format("Пользователь с данным id: %d не найден.", id));
    }

}
