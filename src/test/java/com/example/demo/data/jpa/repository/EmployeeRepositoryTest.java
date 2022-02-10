package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Employee;
import com.example.demo.data.jpa.entity.FullTimeEmployee;
import com.example.demo.data.jpa.entity.PartTimeEmployee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    //@Test
    void insert() {
        repository.insert(new FullTimeEmployee("Employee1", new BigDecimal(1000)));
        repository.insert(new PartTimeEmployee("Employee2", new BigDecimal(50)));

        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);
    }

    //@Test
    void findAll() {
        List<Employee> employees = repository.findAll();
        assertTrue(employees.size() > 0);
    }

    @Test
    void findAllPartTimeEmployees() {
        repository.insert(new PartTimeEmployee("Employee2", new BigDecimal(50)));
        List<Employee> employees = repository.findAllPartTimeEmployees();
        assertTrue(employees.size() > 0);
    }

    @Test
    void findAllFullTimeEmployees() {
        repository.insert(new FullTimeEmployee("Employee1", new BigDecimal(1000)));
        List<Employee> employees = repository.findAllFullTimeEmployees();
        assertTrue(employees.size() > 0);
    }
}