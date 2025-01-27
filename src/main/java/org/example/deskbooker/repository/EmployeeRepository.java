package org.example.deskbooker.repository;

import org.example.deskbooker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Object> findByEmail(String email);
}
