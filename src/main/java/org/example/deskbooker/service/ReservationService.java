package org.example.deskbooker.service;

import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Employee;
import org.example.deskbooker.model.Reservation;
import org.example.deskbooker.repository.DeskRepository;
import org.example.deskbooker.repository.EmployeeRepository;
import org.example.deskbooker.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final DeskRepository deskRepository;
    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, DeskRepository deskRepository, EmployeeRepository employeeRepository) {
        this.reservationRepository = reservationRepository;
        this.deskRepository = deskRepository;
        this.employeeRepository = employeeRepository;
    }

    public Reservation getReservation(String id) {
        return reservationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) {
        return validateAndSetDeskAndEmployee(reservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        reservationRepository.findById(reservation.getId())
            .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + reservation.getId() + " does not exist."));
        return validateAndSetDeskAndEmployee(reservation);
    }

    public void deleteReservation(String id) {
        long reservationId = Long.parseLong(id);
        reservationRepository.findById(reservationId)
                        .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist."));
        reservationRepository.deleteById(Long.valueOf(id));
    }

    private Reservation validateAndSetDeskAndEmployee(Reservation reservation) {
        if (reservation.getDesk() != null && reservation.getDesk().getId() != null) {
            Desk desk = deskRepository.findById(reservation.getDesk().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Desk with ID " + reservation.getDesk().getId() + " not found"));
            reservation.setDesk(desk);
        } else {
            reservation.setDesk(null);
        }

        if (reservation.getEmployee() != null && reservation.getEmployee().getId() != null) {
            Employee employee = employeeRepository.findById(reservation.getEmployee().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + reservation.getEmployee().getId() + " not found"));
            reservation.setEmployee(employee);
        } else {
            reservation.setEmployee(null);
        }
        return reservationRepository.save(reservation);
    }

}
