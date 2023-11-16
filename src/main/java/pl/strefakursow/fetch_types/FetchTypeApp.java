package pl.strefakursow.fetch_types;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.fetch_types.entity.Company;
import pl.strefakursow.fetch_types.entity.CompanyDetail;
import pl.strefakursow.fetch_types.entity.Property;

public class FetchTypeApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyDetail.class);
        configuration.addAnnotatedClass(Property.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        int id = 24;

        session.beginTransaction();

        System.out.println("Pobieranie obiektu Company");
        Company company = session.get(Company.class, id);
        System.out.println("Pobrano obiekt Company");
        System.out.println(company);

        System.out.println("Nieruchomości:");
        for (Property property : company.getProperties()) {
            System.out.println(property);
        }

        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();
    }

}
