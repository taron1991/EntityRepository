package EntityRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class AdRepository {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Ad.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    private static void addToRepo(Ad ad) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(ad);
        session.getTransaction().commit();
    }


    private static List<Ad> showLastAds() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Ad> query = session.createQuery("from Ad where date =:date", Ad.class);
        query.setParameter("date", LocalDate.now());
        return query.getResultList();
    }

    private static List<Ad> url_Photo() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Ad> query = session.createQuery("from Ad where url is null ", Ad.class);
        return query.getResultList();
    }

    private static List<Ad> findByName(String brand){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Ad> query = session.createQuery("from Ad where brand =:brand", Ad.class);
        query.setParameter("brand",brand);
        return query.getResultList();
    }
}
