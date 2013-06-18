package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.FolderEntity;
import edu.tsystems.demail.model.MailBoxEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 11:12
 */
public class FolderDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

    public List<FolderEntity> getFoldersByUserId(int userId) {
        TypedQuery<FolderEntity> foldersQuery = em.createNamedQuery("FolderEntity.getFoldersByUserId", FolderEntity.class)
                .setParameter("userId", userId);

        return foldersQuery.getResultList();
    }


    public void createDefaultFoldersForMailBox(MailBoxEntity mailBox) {
        System.out.println("DAO LEVEL: " + mailBox);
        FolderEntity incoming = new FolderEntity();
        incoming .setMailBox(mailBox);
        incoming.setName("Inbox");

        FolderEntity sent = new FolderEntity();
        sent.setMailBox(mailBox);
        sent.setName("Outbox");

        FolderEntity trash = new FolderEntity();
        trash.setMailBox(mailBox);
        trash.setName("Trash");

        em.persist(incoming);
        em.persist(sent);
        em.persist(trash);
    }


    public int getFolderIdByNameAndUserId(String name, int userId) {
        TypedQuery<FolderEntity> queryFolder = em.createNamedQuery("FolderEntity.getFolderByNameAndUserId", FolderEntity.class)
                .setParameter("name", name)
                .setParameter("userId", userId);

        List<FolderEntity> folderEntities = queryFolder.getResultList();
        if(folderEntities.size() != 1) return -1;

        return folderEntities.get(0).getId();
    }
    public void create (FolderEntity folderEntity){
        em.getTransaction().begin();
        em.merge(folderEntity);
        em.getTransaction().commit();
    }
}
