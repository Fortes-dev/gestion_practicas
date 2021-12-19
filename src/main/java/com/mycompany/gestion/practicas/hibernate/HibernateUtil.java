package com.mycompany.gestion.practicas.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SF;

    static {
        try {
            SF = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Error al crear SessionFactory");
            System.out.println(e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SF;
    }
    
}
