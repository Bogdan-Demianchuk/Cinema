package com.cinema.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Err can't create Sasion Factory", e);
        }
    }
}
