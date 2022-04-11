package Homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            init();
            AVGresultRUDN();
            //AVGresultRUDN();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void AVGresultStankin() {

        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("  select university,avg(salary_by_university) " +
                    "as avg from Pupils where salary_by_university>4000 " +
                    "group by university having university='МГТУ Станкин'");

            boolean next = resultSet.next();

            while (next) {
                System.out.println(resultSet.getString(1) + " , среднее значение " + resultSet.getDouble(2));
                break;
            }

            if (!next) {
                System.out.println("среднего результата нет");
                return;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select full_name,salary_by_university,university from pupils " +
                    "where university='МГТУ Станкин' order by salary_by_university asc");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2)
                        + " " + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void AVGresultMGTU()  {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("  select university,avg(salary_by_university) " +
                    "as avg from Pupils where salary_by_university>4000 " +
                    "group by university having university='МГТУ им.Баумана'");

            boolean next = resultSet.next();

            while (next) {
                System.out.println(resultSet.getString(1) + " , среднее значение " + resultSet.getDouble(2));
                break;
            }

            if (!next) {
                System.out.println("среднего результата нет");
                return;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select full_name,salary_by_university,university from pupils " +
                    "where university='МГТУ им.Баумана' order by salary_by_university asc;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2)
                        + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void AVGresultMIREA()  {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("  select university,avg(salary_by_university) " +
                    "as avg from Pupils where salary_by_university>4000 " +
                    "group by university having university='МИРЭА'");

            boolean next = resultSet.next();

            while (next) {
                System.out.println(resultSet.getString(1) + " , среднее значение " + resultSet.getDouble(2));
            }

            if (!next) {
                System.out.println("среднего результата нет");
                return;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select full_name,salary_by_university,university from pupils " +
                    "where university='МИРЭА' order by salary_by_university asc;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2)
                        + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void AVGresultRUDN()  {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("  select university,avg(salary_by_university) " +
                    "as avg from Pupils where salary_by_university>4000 " +
                    "group by university having university='РУДН'");

            boolean next = resultSet.next();

            while (next) {
                System.out.println(resultSet.getString(1) + " , среднее значение " + resultSet.getDouble(2));
            }


            if (!next) {
                System.out.println("среднего результата нет");
                return;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select full_name,salary_by_university,university from pupils " +
                    "where university='РУДН' order by salary_by_university asc;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2)
                        + " " + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void init()  {
        InputStream inputStream = Main
                .class
                .getClassLoader()
                .getResourceAsStream("app.properties");

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
