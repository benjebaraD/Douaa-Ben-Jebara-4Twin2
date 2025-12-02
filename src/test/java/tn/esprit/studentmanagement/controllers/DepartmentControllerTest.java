package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartment() {
        Department dep = new Department();
        when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(dep));

        List<Department> result = departmentController.getAllDepartment();

        assertEquals(1, result.size());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartment() {
        Department dep = new Department();
        when(departmentService.getDepartmentById(1L)).thenReturn(dep);

        Department result = departmentController.getDepartment(1L);

        assertNotNull(result);
        verify(departmentService, times(1)).getDepartmentById(1L);
    }

    @Test
    void testCreateDepartment() {
        Department dep = new Department();
        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        Department result = departmentController.createDepartment(dep);

        assertNotNull(result);
        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testUpdateDepartment() {
        Department dep = new Department();
        when(departmentService.saveDepartment(dep)).thenReturn(dep);

        Department result = departmentController.updateDepartment(dep);

        assertNotNull(result);
        verify(departmentService, times(1)).saveDepartment(dep);
    }

    @Test
    void testDeleteDepartment() {
        departmentController.deleteDepartment(1L);

        verify(departmentService, times(1)).deleteDepartment(1L);
    }
}
