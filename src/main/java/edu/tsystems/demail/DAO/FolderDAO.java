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
 * Date: 18.06.13
 * Time: 13:45
 */
public class FolderDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    /**
     * Return folder's list by user id
     * @param userId user id
     * @return list of folders
     */
    public List<FolderEntity> getFoldersByUserId(Long userId) {
        TypedQuery<FolderEntity> foldersQuery = em.createNamedQuery("FolderEntity.getFoldersByUserId", FolderEntity.class)
                .setParameter("userId", userId);

        return foldersQuery.getResultList();
    }

    /**
     * Create system folders: incoming, sent, trash for mail box by id.
     * @param mailBox mail box id
     */
    public void createDefaultFoldersForMailBox(MailBoxEntity mailBox) {
        System.out.println("DAO LEVEL: " + mailBox);
        FolderEntity incoming = new FolderEntity();
        incoming.setMailBox(mailBox);
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

    /**
     * Search incoming folder by name and user id.
     * @param name folder name
     * @param userId user id
     * @return folder id
     */
    public Long getFolderIdByNameAndUserId(String name, Long userId) {
        TypedQuery<FolderEntity> queryFolder = em.createNamedQuery("FolderEntity.getFolderByNameAndUserId", FolderEntity.class)
                .setParameter("name", name)
                .setParameter("userId", userId);

        List<FolderEntity> folderEntities = queryFolder.getResultList();
        if(folderEntities.size() != 1) return null; // @TODO

        return folderEntities.get(0).getId();
    }
}
