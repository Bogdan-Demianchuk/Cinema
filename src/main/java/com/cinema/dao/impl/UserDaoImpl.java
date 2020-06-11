package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.exeption.DataProcessingException;
import com.cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert user to db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session
                    .createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "user by the email from db", e);
        }
    }

    @Override
    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session
                    .createQuery("from User where id = :id", User.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + "user by the id from db", e);
        }
    }
}
