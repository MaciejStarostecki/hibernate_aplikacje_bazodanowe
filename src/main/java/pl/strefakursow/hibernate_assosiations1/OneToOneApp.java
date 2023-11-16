package pl.strefakursow.hibernate_assosiations1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations1.entity.Company;
import pl.strefakursow.hibernate_assosiations1.entity.CompanyDetail;

public class OneToOneApp {

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
                .employeeNumber(150)
                .build();

        Company company = Company.builder()
                .name("Strefa Kursów")
                .value(100000)
                .companyDetail(companyDetail)
                .build();


        session.beginTransaction();

        session.save(companyDetail);
        session.save(company);


        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
