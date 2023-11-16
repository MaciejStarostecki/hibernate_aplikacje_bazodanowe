package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.entity.Employee;

import java.util.List;

public class NamedParametersApp {

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

        String employeeName = "Steven";
        String employeeLastName = "King";

        session.beginTransaction();

        String namedParametersString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName =:firstName and e.lastName=:lastName";

        Query query = session.createQuery(namedParametersString);
        query.setParameter("firstName", employeeName);
        query.setParameter("lastName", employeeLastName);

        List<Object[]> resultList = query.getResultList();

        session.getTransaction().commit();

        for (Object[] object : resultList) {
            System.out.println("First name: " + object[0] + ". Lastname: " + object[1] + ". Salary: "+object[2]);
        }

        // zamknięcie obiektu SessionFactory
        factory.close();

    }
}
