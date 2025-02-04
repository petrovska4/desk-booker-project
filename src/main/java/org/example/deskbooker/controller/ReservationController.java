package org.example.deskbooker.controller;

import org.example.deskbooker.model.Employee;
import org.example.deskbooker.model.Reservation;
import org.example.deskbooker.service.EmployeeService;
import org.example.deskbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final EmployeeService employeeService;

    @Autowired
    public ReservationController(ReservationService reservationService, EmployeeService employeeService) {
        this.reservationService = reservationService;
        this.employeeService = employeeService;
    }

    @GetMapping("/get-by-id")
    public Reservation getById(@RequestParam String id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/get-all")
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/create")
    public Reservation create(@RequestBody Reservation reservation, Principal principal) {
        String email = principal.getName();
        Long loggedInEmployeeId = employeeService.findEmployeeIdByEmail(email);
        Employee employee = new Employee();
        employee.setId(loggedInEmployeeId);
        reservation.setEmployee(employee);
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/update")
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/get-all-by-employee-id")
    public List<Reservation> getAllByEmployeeId(Principal principal) {
        String email = principal.getName();
        Long loggedInEmployeeId = employeeService.findEmployeeIdByEmail(email);
        return reservationService.getReservationsByEmployeeId(String.valueOf(loggedInEmployeeId));
    }
}
