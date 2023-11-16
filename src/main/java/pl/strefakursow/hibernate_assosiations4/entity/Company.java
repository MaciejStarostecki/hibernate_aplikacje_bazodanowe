package pl.strefakursow.hibernate_assosiations4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "id_company") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompany;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Integer value;

    @OneToOne @JoinColumn(name = "id_company_detail") @Cascade(CascadeType.ALL)
    private CompanyDetail companyDetail;

    @OneToMany(mappedBy = "company", cascade = {jakarta.persistence.CascadeType.MERGE, jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.DETACH, jakarta.persistence.CascadeType.REFRESH})
    private List<Property> properties;

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company")
    private List<Department> departments;

    public void addProperty (Property property) {
        if(properties == null) {
            properties = new ArrayList<>();
        }
        this.properties.add(property);
        property.setCompany(this);
    }

    public void addDepartment (Department department) {
        if(departments == null) {
            departments = new ArrayList<>();
        }
        this.departments.add(department);
    }

    @Override
    public String toString() {
        return "Company{" +
                "idCompany=" + idCompany +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
