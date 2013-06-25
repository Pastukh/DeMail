package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.MailBoxEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 2:34
 */
public class MailBoxDAO {
    private static final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    public MailBoxEntity getMailBoxByName(String name) {
        TypedQuery<MailBoxEntity> mailBoxEntityQuery = em.createNamedQuery("MailBoxEntity.getMailBoxByName", MailBoxEntity.class)
                .setParameter("mailBox",name);

        List<MailBoxEntity> mailBoxEntities = mailBoxEntityQuery.getResultList();
        if(mailBoxEntities.size() != 1) return null;
        return mailBoxEntities.get(0);
    }

    public MailBoxEntity get(int id) {
        return em.find(MailBoxEntity.class, id);
    }

    public int create (MailBoxEntity mailBoxEntity){
        int mailBoxId = 0;
        em.getTransaction().begin();
        mailBoxId = em.merge(mailBoxEntity).getId();
        em.getTransaction().commit();
        return mailBoxId;
    }
}
