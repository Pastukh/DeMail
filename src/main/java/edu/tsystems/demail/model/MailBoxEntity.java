package edu.tsystems.demail.model;

import com.sun.istack.internal.NotNull;

//import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 10.06.13
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "mail_boxes")
public class MailBoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @NotNull
    @Column(name = "name",  length = 30)
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

    public MailBoxEntity(int id, int userId, String mailBox, Date dateCreate, UserEntity user) {
        this.id = id;
        this.userId = userId;
        this.mailBox = mailBox;
        this.dateCreate = dateCreate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
