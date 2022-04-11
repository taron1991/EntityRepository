package Homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Session session;
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    public static void main(String[] args) {


        init2();
        AVGresultStankin();


    }


    private static List AVGresultStankin() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Pupils where university='МГТУ Станкин'");
        List<Pupils> list = query.list();

        List<Pupils> collect = list.stream()
                .filter(x -> x.getSalary_by_university() > 4000)
                .sorted(Comparator.comparingInt(Pupils::getSalary_by_university)).collect(Collectors.toList());


        System.out.println(collect);
        session.getTransaction().commit();
        return list;
    }

    private static List AVGresultMGTU() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Pupils where university='МГТУ им.Баумана'");
        List<Pupils> list = query.list();

        List<Pupils> collect = list.stream()
                .filter(x -> x.getSalary_by_university() > 4000)
                .sorted(Comparator.comparingInt(Pupils::getSalary_by_university)).collect(Collectors.toList());


        System.out.println(collect);
        session.getTransaction().commit();
        return list;
    }

    private static List AVGresultMIREA() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Pupils where university='МИРЭА'");
        List<Pupils> list = query.list();

        double asDouble = list.stream().mapToInt(x -> (Integer) x.getSalary_by_university())
                .filter(x -> x > 4000).average().orElseThrow(NoSuchElementException::new);


        System.out.println(asDouble);
        session.getTransaction().commit();
        return list;
    }
    /**
     * Вывести вуз и среднее значение стипендии в отсортированном виде (в порядке возрастания, по стипендии)
     */
    private static List sortedSalaryAndUniversity(){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT MAX(salary_by_university) as max FROM Pupils ");
        List resultList = query.getResultList();
        System.out.println("Max salary is "+resultList);

        session.getTransaction().commit();
        return resultList;
    }

    /**
     * Найти среднее значение стипендии для каждого ВУЗа превышающее 4000.
     * Задачу решаем 2 способами: через нативный SQL и через STREAM API
     * @return double value
     */
    private static List AVGresultRUDN() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Pupils where university='РУДН'");
        List<Pupils> list = query.list();

        double asDouble = list.stream().mapToInt(x -> (Integer) x.getSalary_by_university())
                .filter(x -> x > 4000).average().orElseThrow(NoSuchElementException::new);

        System.out.println(asDouble);
        session.getTransaction().commit();
        return list;
    }


    private static void addPupil(Pupils pupils) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(pupils);
        session.getTransaction().commit();
    }

    private static void init() {
        serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(serviceRegistry)
                .buildMetadata().buildSessionFactory();

    }

    private static void init2() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Pupils.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();


    }
}
