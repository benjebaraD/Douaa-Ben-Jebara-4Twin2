package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Student s1 = new Student();
        Student s2 = new Student();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setIdStudent(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdStudent());
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student saved = studentService.saveStudent(student);

        assertNotNull(saved);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        Long id = 1L;

        studentService.deleteStudent(id);

        verify(studentRepository, times(1)).deleteById(id);
    }
}
