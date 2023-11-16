package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.entity.Employee;

import java.util.List;
import java.util.Objects;

public class SelectApp {

    public static final String SELECT_FIRST_NAME_LAST_NAME_FROM_EMPLOYEE = "select firstName, lastName from Employee";
    public static final String SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_AS_E = "select e.firstName, e.lastName from Employee as e";
    public static final String SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_E = "select e.firstName, e.lastName from Employee e";

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

        Query query = session.createQuery(SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_E);

        List<Object[]> resultList = query.getResultList();

        session.getTransaction().commit();

        for (Object[] object : resultList) {
            System.out.println("First name: " + object[0] + ". Lastname: " + object[1]);
        }

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
