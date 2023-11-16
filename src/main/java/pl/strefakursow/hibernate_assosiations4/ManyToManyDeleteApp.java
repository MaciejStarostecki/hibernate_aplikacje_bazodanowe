package pl.strefakursow.hibernate_assosiations4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations4.entity.*;

import java.util.List;

public class ManyToManyDeleteApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyDetail.class);
        configuration.addAnnotatedClass(Property.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Training.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        int idMiley = 120;

        int idTraining = 2;

        session.beginTransaction();

//        Employee employee = session.get(Employee.class, idMiley);
//
//        session.delete(employee);

        Training training = session.get(Training.class, idTraining);

        session.delete(training);


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();
    }

}
