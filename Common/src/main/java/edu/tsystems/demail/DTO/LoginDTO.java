package edu.tsystems.demail.DTO;

/**
 * Author: Ivan Pastukh
 * Date: 12.06.13
 * Time: 13:31
 */
public class LoginDTO extends BaseDTO {
    private String login;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}
