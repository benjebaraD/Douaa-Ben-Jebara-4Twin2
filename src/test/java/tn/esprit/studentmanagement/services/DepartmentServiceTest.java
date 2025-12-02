package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartments() {
        Department d1 = new Department();
        Department d2 = new Department();
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void testGetDepartmentById() {
        Department department = new Department();
        department.setIdDepartment(1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentService.getDepartmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdDepartment());
    }

    @Test
    void testGetDepartmentById_NotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> departmentService.getDepartmentById(1L));

        assertEquals("Department not found", exception.getMessage());
    }

    @Test
    void testSaveDepartment() {
        Department d = new Department();
        when(departmentRepository.save(d)).thenReturn(d);

        Department saved = departmentService.saveDepartment(d);

        assertNotNull(saved);
        verify(departmentRepository, times(1)).save(d);
    }

    @Test
    void testDeleteDepartment() {
        Long id = 1L;

        departmentService.deleteDepartment(id);

        verify(departmentRepository, times(1)).deleteById(id);
    }
}
