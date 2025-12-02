package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEnrollments() {
        Enrollment e1 = new Enrollment();
        Enrollment e2 = new Enrollment();

        when(enrollmentRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Enrollment> result = enrollmentService.getAllEnrollments();

        assertEquals(2, result.size());
        verify(enrollmentRepository, times(1)).findAll();
    }

    @Test
    void testGetEnrollmentById() {
        Enrollment enrollment = new Enrollment();
        enrollment.setIdEnrollment(1L);

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Enrollment result = enrollmentService.getEnrollmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdEnrollment());
    }

    @Test
    void testGetEnrollmentById_NotFound() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> enrollmentService.getEnrollmentById(1L));

        assertEquals("Enrollment not found", exception.getMessage());
    }

    @Test
    void testSaveEnrollment() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment saved = enrollmentService.saveEnrollment(enrollment);

        assertNotNull(saved);
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    void testDeleteEnrollment() {
        Long id = 1L;

        enrollmentService.deleteEnrollment(id);

        verify(enrollmentRepository, times(1)).deleteById(id);
    }
}
