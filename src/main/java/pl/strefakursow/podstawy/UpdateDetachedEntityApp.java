package pl.strefakursow.podstawy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.podstawy.entity.Employee;

public class UpdateDetachedEntityApp {

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

        session.beginTransaction();

        Employee employee = session.get(Employee.class, 10);

        session.getTransaction().commit();

        System.out.println("Dane pracownika: " + employee);

        employee.setFirstName("Marcin");

        session = factory.getCurrentSession();

        session.beginTransaction();

        session.update(employee);

        session.getTransaction().commit();

        System.out.println("Zaktualizowane dane pracownika: " + employee);

        // zamknięcie obiektu SessionFactory
        factory.close();

    }
}
