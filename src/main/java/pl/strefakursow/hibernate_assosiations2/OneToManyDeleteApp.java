package pl.strefakursow.hibernate_assosiations2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations2.entity.Company;
import pl.strefakursow.hibernate_assosiations2.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations2.entity.Property;

public class OneToManyDeleteApp {

    public static final String SELECT_C_FROM_COMPANY_C_WHERE_C_NAME_MBANK = "select c from Company c where c.name='mBank'";

    public static void main(String[] args) {

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

        Query query = session.createQuery(SELECT_C_FROM_COMPANY_C_WHERE_C_NAME_MBANK);
        Company company = (Company) query.getSingleResult();



        for (Property property : company.getProperties()) {
            if ("Gdynia".equals(property.getCity())) {
                session.delete(property);
            }
        }

        int idToDelete = 2;
        Property property = session.get(Property.class, idToDelete);

        session.delete(property);

        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
