package repositories;

import models.Curier;
import models.Pachet;
import models.StatusAngajat;
import models.StatusPachet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RepoPackage {
    private SessionFactory sessionFactory;

    public RepoPackage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Pachet> getAllAvailablePackagesForCurrentDay(){
        List<Pachet> pachete = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                pachete =
                        session.createQuery("from models.Pachet where statusPachet = 'livrareAziDisponibil'", Pachet.class).
                                list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                if (tx != null)
                    tx.rollback();
            }
        }
        return pachete;
    }

    public List<Pachet> getAllPackagesFromDeposit(){
        List<Pachet> pachete = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                pachete =
                        session.createQuery("from models.Pachet where statusPachet = 'inDepozit'", Pachet.class).
                                list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                if (tx != null)
                    tx.rollback();
            }
        }
        return pachete;
    }

    public List<Pachet> getPackagesForCurier(Curier curier){
        List<Pachet> pachete = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                pachete =
                        session.createQuery("from models.Pachet where id in " +
                                "(select pachet.id from models.ZiDeLivrare as z WHERE z.curier.id = :curier_id)", Pachet.class)
                                .setParameter("curier_id", curier.getId())
                                .list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                if (tx != null)
                    tx.rollback();
            }
        }
        return pachete;
    }

    public List<Pachet> getAllPackagesDeliveredByCurier(Curier curier){
        List<Pachet> pachete = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                pachete =
                        session.createQuery("from models.Pachet where statusPachet = 'livrat' and id in " +
                                "(select pachet.id from models.ZiDeLivrare as z WHERE z.curier.id = :curier_id)", Pachet.class)
                                .setParameter("curier_id", curier.getId())
                                .list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                if (tx != null)
                    tx.rollback();
            }
        }
        return pachete;
    }



    public Pachet modifyStatus(Pachet pachet, StatusPachet newStatusPachet){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                pachet.setStatusPachet(newStatusPachet);
                session.update(pachet);
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }
        return pachet;
    }


    public Pachet addPackage(Pachet pachet){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                pachet.setId((Long) session.save(pachet));
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }
        return pachet;
    }
}
