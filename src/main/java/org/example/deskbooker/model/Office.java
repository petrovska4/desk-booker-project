package org.example.deskbooker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.deskbooker.model.enumeration.OfficeTypeEnum;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name  = "office")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private OfficeTypeEnum type;

    @OneToMany(mappedBy = "office", fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnoreProperties({"office"})
    private List<Desk> desks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfficeTypeEnum getType() {
        return type;
    }

    public void setType(OfficeTypeEnum type) {
        this.type = type;
    }

    public List<Desk> getDesks() {
        return desks;
    }
}
