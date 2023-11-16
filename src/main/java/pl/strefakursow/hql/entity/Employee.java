package pl.strefakursow.hql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id @Column(name = "id_employee") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmployee;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

}
