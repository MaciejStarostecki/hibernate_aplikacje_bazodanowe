package pl.strefakursow.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.entity.Employee;

import java.util.List;

public class UpdateApp {

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

        int idEmployee = 116;
        int salaryEmployee = 15000;

        session.beginTransaction();

        String update = "update Employee e set e.salary=:salary where e.idEmployee=:idEmployee";

        Query query = session.createQuery(update);
        query.setParameter("salary", salaryEmployee);
        query.setParameter("idEmployee", idEmployee);
        query.executeUpdate();

        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
