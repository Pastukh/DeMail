package edu.tsystems.demail;

import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.model.UserDAO;
import edu.tsystems.demail.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 06.06.13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public class App {
    public static void main (String[] args){
        new Presentation().start();
//        List<UserEntity> users;
//
//        UserDAO dao = new UserDAO();
//        users = dao.getUserByLogin("user");
//        for (UserEntity u: users){
//            System.out.println(u);
//        }

//        users = dao.getAll();
//        for (UserEntity u: users){
//            System.out.println(u);
//        }

    }
}

