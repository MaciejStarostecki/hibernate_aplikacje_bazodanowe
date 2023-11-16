package pl.strefakursow.hibernate_assosiations1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations1.entity.Company;
import pl.strefakursow.hibernate_assosiations1.entity.CompanyDetail;

public class CascadeApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyDetail.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        CompanyDetail companyDetail = CompanyDetail.builder()
                .residence("Poland")
                .employeeNumber(33935)
                .build();

        Company company = Company.builder()
                .name("KGHM")
                .value(46946876)
                .companyDetail(companyDetail)
                .build();


        session.beginTransaction();

        session.persist(company);


        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
