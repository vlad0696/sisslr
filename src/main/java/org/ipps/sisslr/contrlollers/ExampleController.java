package org.ipps.sisslr.contrlollers;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.ipps.sisslr.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;


@Controller
@RequestMapping("/example")
public class ExampleController {

    public List getUsers(){
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = getSession();

            session = sessionFactory.openSession();
            session.beginTransaction();
            List list= session.createCriteria(Users.class).list();

            return list;
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(Users user){
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = getSession();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println(user.toString());
           // session.save(user);

            session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    public  void updateUser(){
        System.out.println("azaza");
    }

    static SessionFactory getSession(){
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
