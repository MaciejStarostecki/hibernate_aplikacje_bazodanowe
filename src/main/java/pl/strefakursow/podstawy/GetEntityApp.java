package pl.strefakursow.podstawy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.podstawy.entity.Employee;

public class GetEntityApp {

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
                .firstName("Tadeusz")
                .lastName("Wiśniewski")
                .salary(10000)
                .build();


        // rozpoczęcie transakcji
        session.beginTransaction();

        // zapisanie pracownika
        Integer id = (Integer) session.save(employee);

        // zakończenie transakcji
        session.getTransaction().commit();


        session = factory.getCurrentSession();

        session.beginTransaction();

        Employee retrievedEmployee1 = session.get(Employee.class, id);

        session.getTransaction().commit();

        System.out.println("Dane pracownika: " + retrievedEmployee1);

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
