package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hql.entity.Employee;

import java.util.List;

public class FromApp {

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

        String from = "FROM Employee";

        Query query = session.createQuery(from);

        List<Employee> employeeList = query.getResultList();

        for (Employee employee : employeeList) {

            System.out.println(employee);

        }


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
