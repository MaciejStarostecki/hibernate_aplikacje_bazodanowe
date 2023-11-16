package pl.strefakursow.hibernate_assosiations1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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



}
