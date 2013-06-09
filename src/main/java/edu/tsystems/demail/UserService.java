package edu.tsystems.demail;

import edu.tsystems.demail.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 07.06.13
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    public static EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

    public List<UserEntity> getAll(){
        TypedQuery<UserEntity> namedQuery = (TypedQuery<UserEntity>) em.createNamedQuery("UserEntity.getAll");// .createNativeQuery("UserEntity.getAll", UserEntity.class);
        return namedQuery.getResultList();
    }
   public UserEntity getUser(){
        return em.find(UserEntity.class, 1);
   }
}
