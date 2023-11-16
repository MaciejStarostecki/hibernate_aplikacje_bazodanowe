package pl.strefakursow.hibernate_assosiations4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations4.entity.*;

import java.util.List;

public class ManyToManyGetApp {

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

        int id = 2;

        session.beginTransaction();


//        Training training = session.get(Training.class, id);
//
//        System.out.println(training);
//
//        for (Employee employee : training.getEmployees())
//            System.out.println(employee);

        String getTraining = "from Training";
        Query query = session.createQuery(getTraining);

        List<Training> resultList = query.getResultList();

        for (Training training : resultList) {
            System.out.println(training);
            for (Employee employee : training.getEmployees()) {
                System.out.println(employee);
            }
            System.out.println("\n\n");
        }


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
