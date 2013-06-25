package edu.tsystems.demail.DAO;

import edu.tsystems.demail.model.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 07.06.13
 * Time: 16:30
 */
public class UserDAO {
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
    private static final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
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

    public List<UserEntity> getUserByLogin(String login) {
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByLogin", UserEntity.class)
                .setParameter("login", login);
        if(query.getResultList().size() != 1) {
            System.out.print(login + " not found");
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
