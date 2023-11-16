package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hql.entity.Employee;

import java.util.List;

public class OrderByApp {

    public static final String SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_E_ORDER_BY_E_FIRST_NAME = "select e.firstName, e.lastName from Employee e order by e.firstName";
    public static final String SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_E_ORDER_BY_E_LAST_NAME_DESC = "select e.firstName, e.lastName from Employee e order by e.lastName desc";

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

        Query query = session.createQuery(SELECT_E_FIRST_NAME_E_LAST_NAME_FROM_EMPLOYEE_E_ORDER_BY_E_LAST_NAME_DESC);

        List<Object[]> resultList = query.getResultList();

        session.getTransaction().commit();

        for (Object[] object : resultList) {
            System.out.println("First name: " + object[0] + ". Lastname: " + object[1]);
        }

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
