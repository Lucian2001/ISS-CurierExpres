package repositories;

import models.Curier;
import models.MyUser;
import models.Sef;
import models.StatusAngajat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class RepoUser {
    private SessionFactory sessionFactory;

    public RepoUser(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<? extends MyUser> getUserByUsername(String username){
        Optional<MyUser> user = Optional.empty();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                user = Optional.ofNullable(session.createQuery("from models.MyUser as m where m.nume = :myUser_username", MyUser.class)
                        .setParameter("myUser_username", username)
                        .list().get(0)
                );

                tx.commit();
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                if (tx != null)
                    tx.rollback();
            }
        }
        return user;
    }


    public Sef addSef(Sef myUser){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                myUser.setId((Long) session.save(myUser));
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }
        return myUser;
    }

    public Curier addCurier(Curier myUser){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                myUser.setId((Long) session.save(myUser));
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }
        return myUser;
    }

    public Curier markPresent(Curier curier){
        Curier newCurier = null;
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                curier.setStatus(StatusAngajat.conectat);
                session.update(curier);
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }
        return curier;
    }
}
