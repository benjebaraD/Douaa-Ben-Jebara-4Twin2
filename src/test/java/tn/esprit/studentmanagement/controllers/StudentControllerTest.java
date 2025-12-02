package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private IStudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Student s = new Student();
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(s));

        List<Student> result = studentController.getAllStudents();

        assertEquals(1, result.size());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudent() {
        Student s = new Student();
        when(studentService.getStudentById(1L)).thenReturn(s);

        Student result = studentController.getStudent(1L);

        assertNotNull(result);
        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testCreateStudent() {
        Student s = new Student();
        when(studentService.saveStudent(s)).thenReturn(s);

        Student result = studentController.createStudent(s);

        assertNotNull(result);
        verify(studentService, times(1)).saveStudent(s);
    }

    @Test
    void testUpdateStudent() {
        Student s = new Student();
        when(studentService.saveStudent(s)).thenReturn(s);

        Student result = studentController.updateStudent(s);

        assertNotNull(result);
        verify(studentService, times(1)).saveStudent(s);
    }

    @Test
    void testDeleteStudent() {
        studentController.deleteStudent(1L);

        verify(studentService, times(1)).deleteStudent(1L);
    }
}
