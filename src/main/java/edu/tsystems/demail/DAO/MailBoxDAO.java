package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.MailBoxEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 2:34
 */
public class MailBoxDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    public int create (MailBoxEntity mailBoxEntity){
        int mailBoxId = 0;
        em.getTransaction().begin();
        mailBoxId = em.merge(mailBoxEntity).getId();
        em.getTransaction().commit();
        return mailBoxId;
    }
}
