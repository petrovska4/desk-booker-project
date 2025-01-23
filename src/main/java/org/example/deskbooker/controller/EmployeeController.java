package org.example.deskbooker.controller;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Employee;
import org.example.deskbooker.service.DeskService;
import org.example.deskbooker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get-by-id")
    public Employee getById(@RequestParam String id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/get-all")
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        employeeService.deleteEmployee(id);
    }

}
