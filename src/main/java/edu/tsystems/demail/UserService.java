package edu.tsystems.demail;

import edu.tsystems.demail.DTO.LoginDTO;
import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.model.UserDAO;
import edu.tsystems.demail.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 07.06.13
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    private UserDAO userDAO = new UserDAO();
    ///private MailBoxDAO mailBoxDAO;
    //private FolderDAO folderDAO;
    /**
     * Select user by loginDTO
     * @return user dto
     */
    public UserDTO login(LoginDTO loginDTO) {
        UserEntity userEntity = userDAO.getUserByLoginAndPassword(loginDTO.getLogin(), loginDTO.getPassword());

        if(userEntity == null) return null;

        return createUserDTOFromEntity(userEntity);
    }

    /**
     * Create DTO object from User entity
     * @param userEntity user entity
     * @return user dto
     */
    private UserDTO createUserDTOFromEntity(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setFirstname(userEntity.getFirst_name());
        userDTO.setLastname(userEntity.getLast_name());
        userDTO.setMailBox(userEntity.getMailBox().getMailBox());
        return userDTO;
    }
    private UserEntity createNewUser(String login, String password, String firstname,
                                     String lastname, String phone) {


        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPass(password);
        userEntity.setFirst_name(firstname);
        userEntity.setLast_name(lastname);
        userEntity.setPhone(phone);
        // create new user
        userDAO.create(userEntity);
        return userEntity;
    }
}
