package Parser;

import Persistense.Student;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONParser {

    Student student;

    public List<Student> parse(InputStream stream) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(new InputStreamReader(stream));

        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();

            if (id.startsWith("id")) {
                String name = scanner.nextLine();

                if (name.startsWith("name")) {
                    student = new Student(Long.parseLong(id.substring(6)), name.substring(9, name.length() - 2));
                    students.add(student);
                }
            }
        }
        return students;
    }

    public static String parse(Student student) {
        return "\n{\n"
                + "\"id\": " + student.getId() + ",\n"
                + "\"name\": \"" + student.getName() + "\"\n"
                + "},";
    }
}
