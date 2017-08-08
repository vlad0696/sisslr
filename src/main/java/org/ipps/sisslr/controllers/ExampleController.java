package org.ipps.sisslr.controllers;


import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.ipps.sisslr.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            List list= session.createCriteria(Users.class).list();
            return list;
        }
        finally {
            session.close();
            sessionFactory.close();
            //Hello man, how are you?
        }
    }



    public Object parseRequest(HttpServletRequest request){
        SessionFactory sessionFactory = null;
        Session session = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            sessionFactory = getSession();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Users user = (Users) session.get(Users.class, id);
            tx.commit();
            return user;
        }
        finally {
            session.close();
            sessionFactory.close();
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  void updateUser( Users user, HttpServletResponse response){
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = getSession();
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println(user.toString());
        //    session.save(user);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public  void updateUser(String data, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
    }

    @RequestMapping(value = "/JSON", method = RequestMethod.GET)
    public  void getJSON(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = getSession();

            session = sessionFactory.openSession();
            List list= session.createCriteria(Users.class).list();
            Gson g = new Gson();
            response.getWriter().write(g.toJson(list));
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }

    static SessionFactory getSession(){
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
