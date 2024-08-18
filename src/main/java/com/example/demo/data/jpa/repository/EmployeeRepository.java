package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED) // isolation level to solve dirty read,
                                                    // phantom read and Non repeatable read
public class EmployeeRepository {
    @Autowired
    private EntityManager em;

    public void insert(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findAll() {
        return em.createQuery("from Employee", Employee.class).getResultList();
    }

    public List<Employee> findAllFullTimeEmployees() {
        return em.createQuery("from FullTimeEmployee", Employee.class).getResultList();
    }

    public List<Employee> findAllPartTimeEmployees() {
        return em.createQuery("from PartTimeEmployee", Employee.class).getResultList();
    }
}