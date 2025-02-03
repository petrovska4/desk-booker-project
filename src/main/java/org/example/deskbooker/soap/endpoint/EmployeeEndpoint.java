package org.example.deskbooker.soap.endpoint;

import org.example.deskbooker.GetEmployeeRequest;
import org.example.deskbooker.GetEmployeeResponse;
import org.example.deskbooker.GetEmployeesByPositionRequest;
import org.example.deskbooker.GetEmployeesByPositionResponse;
import org.example.deskbooker.model.Employee;
import org.example.deskbooker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.org/deskbooker";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request){
        GetEmployeeResponse response = new GetEmployeeResponse();

        Employee employee = employeeService.getEmployee(String.valueOf(request.getId()));

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition().name());

        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetEmployeesByPositionRequest")
    @ResponsePayload
    public GetEmployeesByPositionResponse getEmployeesByPosition(@RequestPayload GetEmployeesByPositionRequest request){
        GetEmployeesByPositionResponse response = new GetEmployeesByPositionResponse();

        List<Employee> employees = employeeService.getEmployeesByPosition(request.getPosition());

        for(Employee employee : employees){
            GetEmployeesByPositionResponse.Employee employeeResponse = new GetEmployeesByPositionResponse.Employee();

            employeeResponse.setId(employee.getId());
            employeeResponse.setFirstName(employee.getFirstName());
            employeeResponse.setLastName(employee.getLastName());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setPosition(employee.getPosition().name());

            response.getEmployee().add(employeeResponse);
        }

        return response;

    }
}
