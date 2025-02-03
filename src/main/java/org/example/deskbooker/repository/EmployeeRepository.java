package org.example.deskbooker.repository;

import org.example.deskbooker.model.Employee;
import org.example.deskbooker.model.enumeration.EmployeePositionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findAllByPosition(EmployeePositionEnum positionEnum);
}
