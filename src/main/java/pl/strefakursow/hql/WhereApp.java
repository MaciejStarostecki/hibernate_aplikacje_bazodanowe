package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.entity.Employee;

import java.util.List;

public class WhereApp {

    public static final String FROM_EMPLOYEE_WHERE_FIRST_NAME_TADEUSZ = "from Employee where firstName = 'Tadeusz'";
    public static final String FROM_EMPLOYEE_WHERE_SALARY_MORE_12000 = "from Employee where salary > 12000";
    public static final String FROM_EMPLOYEE_WHERE_SALARY_LESS_3000_OR_SALARY_MORE_13000 = "from Employee where salary < 3000 or salary > 13000";
    public static final String FROM_EMPLOYEE_WHERE_SALARY_IS_NULL = "from Employee where salary is null";
    public static final String FROM_EMPLOYEE_WHERE_LAST_NAME_IN_HUTTON_ERRAZURIZ_WIŚNIEWSKI = "from Employee where lastName in ('Hutton', 'Errazuriz', 'Wiśniewski')";

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

        Query query = session.createQuery(FROM_EMPLOYEE_WHERE_LAST_NAME_IN_HUTTON_ERRAZURIZ_WIŚNIEWSKI);

        List<Employee> employeeList = query.getResultList();

        for (Employee employee : employeeList) {

            System.out.println(employee);

        }


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
