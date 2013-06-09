package edu.tsystems.demail;

import edu.tsystems.demail.model.UserEntity;

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
        //new Presentation().start();

        UserService user = new UserService();
       // System.out.print(user.getUser());
        List<UserEntity> users = user.getAll();
        for (UserEntity u: users){
            System.out.println(u);
        }

    }
}

