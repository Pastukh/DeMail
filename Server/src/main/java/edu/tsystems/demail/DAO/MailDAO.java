package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.BaseEntity;
import edu.tsystems.demail.model.MailBoxEntity;
import edu.tsystems.demail.model.MailEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 20.06.13
 * Time: 16:56
 */
public class MailDAO {
    private static final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

    /**
     * return emails by folder id
     * @param folderId folder id
     * @return list of emails
     */
    public List<MailEntity> getMailByFolderId(int folderId) {
        TypedQuery<MailEntity> mailQuery = em.createNamedQuery("MailEntity.getMailByFolderId", MailEntity.class)
                .setParameter("folderId", folderId);
        return mailQuery.getResultList();
    }
//    public MailEntity get (int id){
//        TypedQuery<MailEntity> mailQuery = em.createNamedQuery("MailEntity.getMailById ", MailEntity.class)
//                .setParameter("Id", id);
//        return mailQuery.getResult();
//    }


    public void create (BaseEntity entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void update(BaseEntity entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(BaseEntity entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
    public MailEntity get(int id) {
        return em.find(MailEntity.class, id);
    }

}
