package Service;

import Connection.FTPServerConnection;
import Exception.StudentNotFoundException;
import Exception.StudentParseException;

import Parser.JSONParser;
import Persistense.Student;

import java.io.*;
import java.util.Comparator;
import java.util.List;

public class StudentService {

    private List<Student> students;

    public StudentService(String login, String password, String ipAddress, String endPoint) {
        FTPServerConnection connection = new FTPServerConnection();
        connection.connect(login, password, ipAddress, endPoint);

        try {
            InputStream reader = connection.findInputStream();
            students = new JSONParser().parse(reader);
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findAll() throws StudentParseException {
        if (!students.isEmpty()) {
            students.sort(Comparator.comparing(Student::getName));
            return students;
        } else {
            throw new StudentParseException("Не удалось найти ни одного пользователя");
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
        long id = students.get(students.size() - 1).getId() + 1;
        Student student = new Student(id, name);
        students.add(student);
    }

    public void deleteById(long id) throws StudentNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                if (!students.remove(student)) {
                    throw new StudentNotFoundException(String.format("Пользователь с данным id: %d не найден.", id));
                }
            }
        }
    }

}
