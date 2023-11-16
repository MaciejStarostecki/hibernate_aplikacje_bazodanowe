package pl.strefakursow.hibernate_assosiations1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.strefakursow.hibernate_assosiations1.entity.Company;
import pl.strefakursow.hibernate_assosiations1.entity.CompanyDetail;

public class CascadeRemoveApp {

    public static void main(String[] args) {

        // stworzenie obiektu Configuration
        Configuration configuration = new Configuration();

        // wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");

        //wczytanie adnotacji
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(CompanyDetail.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Company company = session.get(Company.class, 7);

        //działa po dodaniu kaskadowego usuwania do krotki
        session.remove(company);

        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();

    }
}
