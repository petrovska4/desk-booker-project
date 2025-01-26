package org.example.deskbooker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "desk")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int position;

    @ManyToOne
    @JoinColumn(name = "office_id")
    @JsonBackReference
    private Office office;

    public Long getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public int getPosition() {
        return position;
    }

}
