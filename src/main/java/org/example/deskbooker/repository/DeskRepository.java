package org.example.deskbooker.repository;

import org.example.deskbooker.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DeskRepository extends JpaRepository<Desk, Long> {
    @Query("SELECT d FROM Desk d WHERE d.id NOT IN (" +
            "SELECT r.desk.id FROM Reservation r WHERE (" +
            "(r.startDate < :endDate AND r.endDate > :startDate))" +
            ")")
    List<Desk> findAvailableDesks(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
