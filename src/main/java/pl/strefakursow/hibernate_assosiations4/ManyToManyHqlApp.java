package pl.strefakursow.hibernate_assosiations4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations4.entity.*;

import java.util.List;

public class ManyToManyHqlApp {
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

        int minEmployeeNumber = 6;
        String course = "javascript";
        int trainingNumber = 1;
        int maxSalary = 12000;
        String queryString = "select t from Training t where size(t.employees) >=:minEmployeeNumber";
        String queryString2 = "select e from Employee e join e.trainings t where t.name=:course";
        String queryString3 = "select e from Employee e where size(e.trainings) =:trainingNumber and e.salary <:maxSalary";

        session.beginTransaction();

        Query query = session.createQuery(queryString3);
//        query.setParameter("minEmployeeNumber", minEmployeeNumber);
//        query.setParameter("course", course);
        query.setParameter("trainingNumber", trainingNumber);
        query.setParameter("maxSalary", maxSalary);

//        List<Training> trainings = query.getResultList();
//
//        for (Training training : trainings) {
//            System.out.println(training);
//        }

        List<Employee> resultList = query.getResultList();

        for (Employee employee : resultList) {
            System.out.println(employee);
        }


        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();
    }
}
