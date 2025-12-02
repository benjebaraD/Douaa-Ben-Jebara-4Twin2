package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.services.IEnrollment;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentControllerTest {

    @Mock
    private IEnrollment enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEnrollment() {
        Enrollment e = new Enrollment();
        when(enrollmentService.getAllEnrollments()).thenReturn(Arrays.asList(e));

        List<Enrollment> result = enrollmentController.getAllEnrollment();

        assertEquals(1, result.size());
        verify(enrollmentService, times(1)).getAllEnrollments();
    }

    @Test
    void testGetEnrollment() {
        Enrollment e = new Enrollment();
        when(enrollmentService.getEnrollmentById(1L)).thenReturn(e);

        Enrollment result = enrollmentController.getEnrollment(1L);

        assertNotNull(result);
        verify(enrollmentService, times(1)).getEnrollmentById(1L);
    }

    @Test
    void testCreateEnrollment() {
        Enrollment e = new Enrollment();
        when(enrollmentService.saveEnrollment(e)).thenReturn(e);

        Enrollment result = enrollmentController.createEnrollment(e);

        assertNotNull(result);
        verify(enrollmentService, times(1)).saveEnrollment(e);
    }

    @Test
    void testUpdateEnrollment() {
        Enrollment e = new Enrollment();
        when(enrollmentService.saveEnrollment(e)).thenReturn(e);

        Enrollment result = enrollmentController.updateEnrollment(e);

        assertNotNull(result);
        verify(enrollmentService, times(1)).saveEnrollment(e);
    }

    @Test
    void testDeleteEnrollment() {
        enrollmentController.deleteEnrollment(1L);

        verify(enrollmentService, times(1)).deleteEnrollment(1L);
    }
}
