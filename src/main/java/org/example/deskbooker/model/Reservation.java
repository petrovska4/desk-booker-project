package org.example.deskbooker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}
