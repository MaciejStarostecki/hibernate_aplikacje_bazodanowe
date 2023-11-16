package pl.strefakursow.hibernate_assosiations1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations1.entity.Company;
import pl.strefakursow.hibernate_assosiations1.entity.CompanyDetail;

public class OneToOneHqlApp {

    public static final String SELECT_C_FROM_COMPANY_C = "select c from Company c";
    public static final String COMPANY_WHICH_HAVE_RESIDENCE_IN_ITALY = "select c from Company c join c.companyDetail cd where cd.residence='Italy'";
    public static final String SUM_OF_VALUE_COMPANIES_WHICH_HAVE_RESIDENCE_IN_POLAND = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";

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

        Query query = session.createQuery(SUM_OF_VALUE_COMPANIES_WHICH_HAVE_RESIDENCE_IN_POLAND);

//        List<Company> resultList = query.getResultList();

        Long singleResult = (Long) query.getSingleResult();

        session.getTransaction().commit();

//        for (Company company : resultList) {
//            System.out.println(company + ", " + company.getCompanyDetail());
//        }

        System.out.println("Wartość firm mających siedzibę w Polsce: " +singleResult);

        // zamknięcie obiektu SessionFactory
        factory.close();

    }

}
