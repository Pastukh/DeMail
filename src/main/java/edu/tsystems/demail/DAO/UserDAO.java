package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 07.06.13
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    private EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    public UserDAO() {

    }

    public UserEntity getUser(int id){
        return em.find(UserEntity.class, id);
    }
    public List<UserEntity> getAll(){
        TypedQuery<UserEntity> namedQuery = (TypedQuery<UserEntity>) em.createNamedQuery("UserEntity.getAll");
        return namedQuery.getResultList();
    }

    public UserEntity getUserByLoginAndPassword(String login, String password) {
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByLoginAndPassword", UserEntity.class)
                .setParameter("login", login)
                .setParameter("password", password);

        List<UserEntity> result = query.getResultList();

        if(result.size() != 1) return null;
        return result.get(0);
    }
    /**
     * search user by login
     * @param login login
     * @return list of entity
     */
    public List<UserEntity> getUserByLogin(String login) {
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByLogin", UserEntity.class)
                .setParameter("login", login);
        if(query.getResultList().size() != 1) {
            System.out.print("not found");
            return null;
        }
        return query.getResultList();
    }
    public int create (UserEntity user){
        int userId = 0;
        em.getTransaction().begin();
        userId = em.merge(user).getId();
        em.getTransaction().commit();
        return userId;
    }
}
