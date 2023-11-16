package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.entity.Employee;

public class AggregateFunctionApp {

    public static final String AVERAGE_SALARY = "select avg(e.salary) from Employee e";
    public static final String SUM_SALARY = "select sum (e.salary) from Employee e";
    public static final String MIN_SALARY = "select min(e.salary) from Employee e";
    public static final String MAX_SALARY = "select max(e.salary) from Employee e";
    public static final String COUNT_EMPLOYEE = "select count(e) from Employee e";

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

        Query query = session.createQuery(COUNT_EMPLOYEE);

        Long result = (Long) query.getSingleResult();

        session.getTransaction().commit();

        System.out.println("Wynik: " + result);

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
