package pl.strefakursow.hibernate_assosiations2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "property")
public class Property {

    @Id @Column(name = "id_property") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProperty;

    @Column(name = "city")
    private String city;

    @Column(name = "room_number")
    private Integer roomNumber;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_company")
    private Company company;


    @Override
    public String toString() {
        return "Property{" +
                "idProperty=" + idProperty +
                ", city='" + city + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
