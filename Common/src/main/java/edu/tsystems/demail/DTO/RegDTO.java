package edu.tsystems.demail.DTO;

/**
 * Author: Ivan Pastukh
 * Date: 19.06.13
 * Time: 7:35
 */
public class RegDTO extends BaseDTO {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;

    public RegDTO() {
    }

    public RegDTO(String login, String password, String firstname, String lastname, String phone) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
