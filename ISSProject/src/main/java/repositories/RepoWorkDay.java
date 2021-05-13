package repositories;

import models.Pachet;
import models.Sef;
import models.ZiDeLucru;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RepoWorkDay {
    private SessionFactory sessionFactory;

    public RepoWorkDay(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPackageWorkDay(ZiDeLucru ziDeLucru){
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                ziDeLucru.setId((Long) session.save(ziDeLucru));
                tx.commit();
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
                if (tx != null){
                    tx.rollback();
                }
            }
        }

    }

    ZiDeLucru getCurrentWorkDay(){
        return null;
    }
}
