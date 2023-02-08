package Service;

import Persistense.Student;
import Connection.FTPServerConnection;
import Parser.StudentsJSONParser;

import Exception.StudentNotFoundException;
import Exception.StudentParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentService {

    private List<Student> students;

    public StudentService(String login, String password, String IPAddress,String endPoint) {
        FTPServerConnection connection = new FTPServerConnection();
        connection.connect(login, password, IPAddress, endPoint);

        try {
            InputStream reader = connection.findInputStream();
            students = new StudentsJSONParser().parse(reader);
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> findAll() throws StudentParseException {
        if (!students.isEmpty()) {
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

    public void save(Student student) {

    }

    public void deleteById(long id) {

    }
}
