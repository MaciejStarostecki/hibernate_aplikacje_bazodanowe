package pl.strefakursow.hibernate_assosiations3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations3.entity.Company;
import pl.strefakursow.hibernate_assosiations3.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations3.entity.Property;
import pl.strefakursow.hibernate_assosiations3.entity.Department;

import java.util.List;

public class OneToManyUniSaveApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyDetail.class);
        configuration.addAnnotatedClass(Property.class);
        configuration.addAnnotatedClass(Department.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        int id = 29;

        session.beginTransaction();

        Company company = session.get(Company.class, 29);

        System.out.println(company);

        Department sales = Department.builder().name("Sales").build();
        Department production = Department.builder().name("Production").build();
        Department hr = Department.builder().name("HR").build();

        company.addDepartment(sales);
        company.addDepartment(production);
        company.addDepartment(hr);

        session.persist(company);


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
