package edu.tsystems.demail.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author: Ivan Pastukh
 * Date: 10.06.13
 * Time: 12:22
 */
@Entity
@Table(name = "mail_boxes")
@NamedQueries({
@NamedQuery(name = "MailBoxEntity.getMailBoxByName",
        query = "SELECT m FROM MailBoxEntity  m WHERE m.mailBox = :mailBox"),
@NamedQuery(name = "MailBoxEntity.getMailBoxById",
					query = "SELECT m FROM MailBoxEntity m WHERE id = :id")
        })
public class MailBoxEntity extends BaseEntity {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "name",  length = 30, nullable = false)
    private String mailBox;
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private java.util.Date dateCreate;
    @OneToOne(mappedBy = "mailBox")
    private UserEntity user;
    @OneToMany(mappedBy = "mailBox")
    private List<FolderEntity> folders;

    public MailBoxEntity() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "MailBoxEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", mailBox='" + mailBox + '\'' +
                ", dateCreate=" + dateCreate +
                ", folders=" + folders +
                '}';
    }

}
