package org.example.deskbooker.model;

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
    private long id;
    private int position;
}
