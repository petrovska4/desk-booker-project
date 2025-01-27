package org.example.deskbooker.controller;

import org.example.deskbooker.model.Employee;
import org.example.deskbooker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get-by-id")
    public Employee getById(@RequestParam String id) {
        return employeeService.getEmployee(id);
    }

    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @GetMapping("/get-all")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/register")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PreAuthorize("hasRole('ROLE_DIRECTOR')")
    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        employeeService.deleteEmployee(id);
    }

}
