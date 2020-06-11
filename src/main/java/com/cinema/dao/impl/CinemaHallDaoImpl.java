package com.cinema.dao.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.exeption.DataProcessingException;
import com.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert cinemahall to db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession();) {
            Query<CinemaHall> query = session.createQuery("from CinemaHall", CinemaHall.class);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get All cinema hall from db", e);
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery("from CinemaHall where id = :id");
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get cinemaHall by id from db", e);
        }
    }
}
