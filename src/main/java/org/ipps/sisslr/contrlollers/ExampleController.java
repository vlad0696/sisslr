package org.ipps.sisslr.contrlollers;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.ipps.sisslr.models.Address;
import org.ipps.sisslr.models.Users;
import org.springframework.stereotype.Controller;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ExampleController {

    public static Connection getPostgresConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test","postgres", "qwerty123");
            return connection;
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Address> getHello() throws SQLException {
        Connection connection = getPostgresConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs =stmt.executeQuery("select id, city from public.address;");
        ArrayList<Address> list = new ArrayList<Address>();

        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  city = rs.getString("city");
            Address address= new Address();
            address.setId(id);
            address.setCity(city);
            list.add(address);
        }

        return list;
    }

    public List<Users> getUsers(){
        SessionFactory sessionFactory  = getSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Users> list= session.createCriteria(Users.class).list();
        session.close();
        return list;
    }

    public  static SessionFactory getSession(){
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
    }

  /* public  void testhibernate(){
        SessionFactory sessionFactory  = getSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();
    }*/

}
