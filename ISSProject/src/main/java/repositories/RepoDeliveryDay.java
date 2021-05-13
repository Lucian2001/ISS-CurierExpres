package repositories;

import models.ZiDeLivrare;
import models.ZiDeLucru;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RepoDeliveryDay {
    private SessionFactory sessionFactory;

    public RepoDeliveryDay(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPackageDeliveryDay(ZiDeLivrare ziDeLivare){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                ziDeLivare.setId((Long) session.save(ziDeLivare));
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }

    }
}
