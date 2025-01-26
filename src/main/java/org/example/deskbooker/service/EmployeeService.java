package org.example.deskbooker.service;

import org.example.deskbooker.model.Employee;
import org.example.deskbooker.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(String id) {
        return employeeRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + id + " not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee must have an ID to be updated.");
        }
        employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + employee.getId() + " not found"));
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        long employeeId = Long.parseLong(id);
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + id + " not found"));
        employeeRepository.deleteById(employeeId);
    }
}
