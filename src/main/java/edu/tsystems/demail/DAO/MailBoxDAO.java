package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.MailBoxEntity;
import edu.tsystems.demail.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 2:34
 */
public class MailBoxDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    public void create (MailBoxEntity mailBoxEntity){
        em.getTransaction().begin();
        em.merge(mailBoxEntity);
        em.getTransaction().commit();
    }
}
