package pl.strefakursow.hibernate_assosiations2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations2.entity.Company;
import pl.strefakursow.hibernate_assosiations2.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations2.entity.Property;

import java.util.List;

public class OneToManyHqlApp {

    public static final String NAME_OF_COMPANIES_WHICH_HAVE_PROPERTY_IN_SEVILLA = "select c.name from Company c join c.properties p where p.city='Sevilla'";
    public static final String NAME_OF_COMPANIES_WHICH_HAVE_RESIDENCE_IN_SWITZERLAND_AND_PROPERTY_IN_BARCELONA = "select c.name from Company c join c.properties p join c.companyDetail cd where p.city='Barcelona' and cd.residence='Switzerland'";
    public static final String NAME_OF_COMPANIES_WHICH_HAVE_MORE_THAN_FOUR_PROPERTIES = "select c.name from Company c where size(c.properties) > 4";

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

        session.beginTransaction();

        Query query = session.createQuery(NAME_OF_COMPANIES_WHICH_HAVE_MORE_THAN_FOUR_PROPERTIES);

        List<String> resultList = query.getResultList();

        for (String name : resultList) {
            System.out.println(name);
        }

        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();
    }
}
