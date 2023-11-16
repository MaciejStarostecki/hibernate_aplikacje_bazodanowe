package pl.strefakursow.hibernate_assosiations2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "company_detail")
public class CompanyDetail {

    @Id @Column(name = "id_company_detail") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompanyDetail;

    @Column(name = "residence")
    private String residence;

    @Column(name = "employee_number")
    private Integer employeeNumber;

    @OneToOne(mappedBy = "companyDetail", cascade = CascadeType.ALL)
    private Company company;

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "idCompanyDetail=" + idCompanyDetail +
                ", residence='" + residence + '\'' +
                ", employeeNumber=" + employeeNumber +
                '}';
    }
}
