package edu.tsystems.demail.DTO;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 12.06.13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class UserDTO {
    private int id;
    private String login;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String mailBox;

    public UserDTO() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }
    @Override
    public String toString() {
        return "UserDTO(id="+id+";login="+login+";firstname="+firstname+";lastname="+lastname+";birthdate="+birthdate
                +";mail_box="+mailBox+")";
    }
}
