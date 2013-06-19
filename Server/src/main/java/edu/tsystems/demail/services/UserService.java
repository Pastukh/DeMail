package edu.tsystems.demail.services;

import edu.tsystems.demail.DTO.LoginDTO;
import edu.tsystems.demail.DTO.RegDTO;
import edu.tsystems.demail.DAO.UserDAO;
import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.model.UserEntity;


/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 07.06.13
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    private static UserDAO userDAO = new UserDAO();
    ///private MailBoxDAO mailBoxDAO;
    //private FolderDAO folderDAO;

    public UserService() {
//        userDAO = new UserDAO();
    }

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

    public UserDTO createNewUser(RegDTO regDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(regDTO.getLogin());
        userEntity.setPass(regDTO.getPassword());
        userEntity.setFirst_name(regDTO.getFirstname());
        userEntity.setLast_name(regDTO.getLastname());
        userEntity.setPhone(regDTO.getPhone());
        // create new user
        userEntity.setId(userDAO.create(userEntity));
        return createUserDTOFromEntity(userEntity);
    }
    private UserDTO createUserDTOFromEntity(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setFirstname(userEntity.getFirst_name());
        userDTO.setLastname(userEntity.getLast_name());
//        userDTO.setMailBox(userEntity.getMailBox().getMailBox());
        return userDTO;
    }
    public UserEntity createNewUser(String login, String password, String firstname,
                                     String lastname, String phone) {


        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPass(password);
        userEntity.setFirst_name(firstname);
        userEntity.setLast_name(lastname);
        userEntity.setPhone(phone);
        // create new user
        userEntity.setId(userDAO.create(userEntity));
        return userEntity;
    }
}
