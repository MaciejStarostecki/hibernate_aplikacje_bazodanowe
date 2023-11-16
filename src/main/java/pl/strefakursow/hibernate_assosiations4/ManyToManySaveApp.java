package pl.strefakursow.hibernate_assosiations4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations4.entity.Company;
import pl.strefakursow.hibernate_assosiations4.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations4.entity.Department;
import pl.strefakursow.hibernate_assosiations4.entity.Property;
import pl.strefakursow.hibernate_assosiations4.entity.Employee;
import pl.strefakursow.hibernate_assosiations4.entity.Training;

public class ManyToManySaveApp {

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
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Training.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Training salesTraining = Training.builder().name("Sales training").build();

        Employee johnny = Employee.builder()
                .firstName("Johnny")
                .lastName("Deep")
                .salary(16000)
                .build();

        Employee miley = Employee.builder()
                .firstName("Miley")
                .lastName("Cyrus")
                .salary(16000)
                .build();

        salesTraining.addEmployee(johnny);
        salesTraining.addEmployee(miley);

        session.persist(salesTraining);

        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
