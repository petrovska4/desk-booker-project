package org.example.deskbooker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.deskbooker.model.enumeration.OfficeTypeEnum;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name  = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private OfficeTypeEnum type;
}
