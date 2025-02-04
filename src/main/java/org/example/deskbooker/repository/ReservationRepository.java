package org.example.deskbooker.repository;

import org.example.deskbooker.model.Employee;
import org.example.deskbooker.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> employee(Employee employee);

    List<Reservation> findAllByEmployee_Id(Long employeeId);
}
