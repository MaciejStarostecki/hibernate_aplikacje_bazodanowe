package pl.strefakursow.hibernate_assosiations3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id_department") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartment;

    @Column(name = "name")
    private String name;


    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", name='" + name + '\'' +
                '}';
    }
}
