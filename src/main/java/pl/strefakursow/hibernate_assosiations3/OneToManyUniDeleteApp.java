package pl.strefakursow.hibernate_assosiations3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.strefakursow.hibernate_assosiations3.entity.Company;
import pl.strefakursow.hibernate_assosiations3.entity.CompanyDetail;
import pl.strefakursow.hibernate_assosiations3.entity.Department;
import pl.strefakursow.hibernate_assosiations3.entity.Property;

public class OneToManyUniDeleteApp {
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

        // stworzenie obiektu SessionFactory
        SessionFactory factory = configuration.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        int idDepartment = 2;
        int idCompany = 29;
        String departmentNameToDelete = "Sales";
        int idHql = 3;

        String delete = "delete Department d where d.idDepartment=:idDepartment";

        session.beginTransaction();

//        Department department = session.get(Department.class, idDepartment);
//        session.delete(department);


//        Company company = session.get(Company.class, idCompany);
//        for (Department department : company.getDepartments()) {
//            if(department.getName().equals(departmentNameToDelete)) {
//                company.getDepartments().remove(department);
//                session.delete(department);
//            }
//        }

        Query query = session.createQuery(delete);
        query.setParameter("idDepartment", idHql);

        query.executeUpdate();

        session.getTransaction().commit();

        // zamknięcie obiektu SessionFactory
        factory.close();


    }

}
