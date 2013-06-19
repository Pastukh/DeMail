package edu.tsystems.demail.DTO;

import java.util.Date;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 2:42
 */
public class MailBoxDTO extends BaseDTO {
    private int id;
    private String mailbox;
    private int userId;
    private Date dateCreated;

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MailBoxDTO{" +
                "id=" + id +
                ", mailbox='" + mailbox + '\'' +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
