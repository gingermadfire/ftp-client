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
    private final FTPServerConnection connection;

    public StudentService(String login, String password, String IPAddress, String endPoint) {
        connection = new FTPServerConnection();
        connection.connect(login, password, IPAddress, endPoint);

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
            students.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            return students;
        } else {
         throw new StudentParseException("Не удалось найти ни одного пользователя");
        }
    }

    public Student findById(long id) throws StudentParseException{
        for (Student student: students) {
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

        try {
            OutputStream outputStream = connection.findOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(JSONParser.parse(student));
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteById(long id) {
        for (Student student: students) {
            if (student.getId() == id) {
                students.remove(student);
                try {
                    OutputStream outputStream = connection.findOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    writer.write(JSONParser.parse(student));
                    writer.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }


            }
        }

    }
}
