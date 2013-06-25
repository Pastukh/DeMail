package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.BaseEntity;
import edu.tsystems.demail.model.FolderEntity;
import edu.tsystems.demail.model.MailBoxEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 11:12
 */
public class FolderDAO {
    private static final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

    public List<FolderEntity> getFoldersByUserId(int userId) {
        TypedQuery<FolderEntity> foldersQuery = em.createNamedQuery("FolderEntity.getFoldersByUserId", FolderEntity.class)
                .setParameter("userId", userId);

        return foldersQuery.getResultList();
    }


    public void createDefaultFoldersForMailBox(MailBoxEntity mailBoxEntity) {
        System.out.println("DAO LEVEL: " + mailBoxEntity);
        FolderEntity inbox = new FolderEntity();
        inbox.setMailBox(mailBoxEntity);
        inbox.setName("Inbox");
        inbox.setSystem(true);

        FolderEntity outbox = new FolderEntity();
        outbox.setMailBox(mailBoxEntity);
        outbox.setName("Outbox");
        outbox.setSystem(true);

        FolderEntity sent = new FolderEntity();
        sent.setMailBox(mailBoxEntity);
        sent.setName("Sent");
        sent.setSystem(true);

        FolderEntity trash = new FolderEntity();
        trash.setMailBox(mailBoxEntity);
        trash.setName("Trash");
        trash.setSystem(true);

        create(inbox);
        create(outbox);
        create(sent);
        create(trash);
    }


    public int getFolderIdByNameAndUserId(String name, int userId) {
        TypedQuery<FolderEntity> queryFolder = em.createNamedQuery("FolderEntity.getFolderByNameAndUserId", FolderEntity.class)
                .setParameter("name", name)
                .setParameter("userId", userId);
        List<FolderEntity> folderEntities = queryFolder.getResultList();
        if(folderEntities.size() != 1) return 1;
        return folderEntities.get(0).getId();
    }

    public FolderEntity get(int id) {
        return em.find(FolderEntity.class, id);
    }
    public void create (FolderEntity folderEntity){
        em.getTransaction().begin();
        em.merge(folderEntity);
        em.getTransaction().commit();
    }
    public void delete(BaseEntity entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
