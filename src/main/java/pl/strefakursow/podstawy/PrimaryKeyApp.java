package pl.strefakursow.podstawy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.entity.Employee;

public class PrimaryKeyApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Employee.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        // stworzenie trzech obiektów employee
        Employee employee = Employee.builder()
                .firstName("Krzysztof")
                .lastName("Nowak")
                .salary(10000)
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Janina")
                .lastName("Kowalska")
                .salary(10000)
                .build();

        Employee employee3 = Employee.builder()
                .firstName("Andrzej")
                .lastName("Sienkiewicz")
                .salary(10000)
                .build();

        // rozpoczęcie transakcji
        session.beginTransaction();

        // zapisanie pracownika
        session.save(employee);
        session.save(employee2);
        session.save(employee3);

        // zakończenie transakcji
        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
