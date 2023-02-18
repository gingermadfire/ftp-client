package com.gingermadfire.service;

import com.gingermadfire.UserLogInData;
import com.gingermadfire.exception.StudentNotFoundException;
import com.gingermadfire.persistense.Student;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeTest()
    public void studentServiceInitialization() {
        studentService = new StudentService(
                UserLogInData.LOGIN.getValue(),
                UserLogInData.PASSWORD.getValue(),
                UserLogInData.IP_ADDRESS.getValue(),
                "/test/students.json"
        );
    }

    @Test
    public void findAllMethodTest() {
        Assert.assertEquals(studentService.findAll(), studentService.getStudents());
    }

    @Test(expectedExceptions = StudentNotFoundException.class, groups = "student service")
    public void emptyStudentsListFindAllMethodTest() {
        List<Student> students = studentService.findAll();
        for (int i = 0; i < students.size(); i++) {
            studentService.deleteById(students.get(i).getId());
        }
        studentService.findAll();
    }

    @Test(expectedExceptions = StudentNotFoundException.class, groups = "student service")
    public void emptyStudentsListFindByIdMethodTest() {
        List<Student> students = studentService.findAll();
        students.forEach(student -> studentService.deleteById(student.getId()));
        studentService.findById(1L);
    }

    @Test(expectedExceptions = StudentNotFoundException.class, groups = "student service")
    public void emptyStudentsListDeleteByIdMethodTest() {
        List<Student> students = studentService.findAll();
        for (int i = 0; i < students.size(); i++) {
            studentService.deleteById(students.get(i).getId());
        }
        studentService.deleteById(1L);
    }


}