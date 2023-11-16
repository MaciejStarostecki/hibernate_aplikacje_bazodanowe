package pl.strefakursow.hibernate_assosiations3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations3.entity.Company;
import pl.strefakursow.hibernate_assosiations3.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations3.entity.Department;
import pl.strefakursow.hibernate_assosiations3.entity.Property;

import java.util.List;

public class OneToManyUniGetApp {

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

//        Company company = session.get(Company.class, 29);
//        System.out.println(company);
//
//        List<Department> departments = company.getDepartments();
//
//        for (Department department : departments) {
//            System.out.println(department);
//        }

        Department department = session.get(Department.class, 2);

        System.out.println(department);

        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();


    }
}
