package pl.strefakursow.hibernate_assosiations4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "training")
public class Training {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training")
    private Integer idTraining;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "employee_training", joinColumns = @JoinColumn(name = "id_training"), inverseJoinColumns = @JoinColumn(name = "id_employee"))
    private List<Employee> employees;

    public void addEmployee(Employee employee) {
        if (employees == null) employees = new ArrayList<>();
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "Training{" +
                "idTraining=" + idTraining +
                ", name='" + name + '\'' +
                '}';
    }
}
