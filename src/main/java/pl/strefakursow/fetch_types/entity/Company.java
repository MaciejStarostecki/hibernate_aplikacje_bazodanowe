package pl.strefakursow.fetch_types.entity;


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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = {jakarta.persistence.CascadeType.MERGE, jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.DETACH, jakarta.persistence.CascadeType.REFRESH})
    private List<Property> properties;

    public void addProperty (Property property) {
        if(properties == null) {
            properties = new ArrayList<>();
        }
        this.properties.add(property);
        property.setCompany(this);
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
