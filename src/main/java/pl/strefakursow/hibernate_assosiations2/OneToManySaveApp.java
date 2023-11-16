package pl.strefakursow.hibernate_assosiations2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations2.entity.Company;
import pl.strefakursow.hibernate_assosiations2.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations2.entity.Property;

public class OneToManySaveApp {

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

        System.out.println(company);

        Property warszawa = Property.builder()
                .city("Warszawa")
                .roomNumber(40)
                .build();

        Property gdynia = Property.builder()
                .city("Gdynia")
                .roomNumber(30)
                .build();

        company.addProperty(warszawa);
        company.addProperty(gdynia);

        session.persist(warszawa);
        session.persist(gdynia);

        session.getTransaction().commit();


        // zamknięcie obiektu SessionFactory
        factory.close();

    }
}
