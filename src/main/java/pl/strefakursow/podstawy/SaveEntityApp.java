package pl.strefakursow.podstawy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.entity.Employee;

public class SaveEntityApp {

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

        // stworzenie obiektu employee
        Employee employee = Employee.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .salary(10000)
                .build();

        // rozpoczęcie transakcji
        session.beginTransaction();

        // zapisanie pracownika
        session.save(employee);

        // zakończenie transakcji
        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();
    }
}
