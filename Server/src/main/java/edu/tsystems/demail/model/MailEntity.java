package edu.tsystems.demail.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Ivan Pastukh
 * Date: 13.06.13
 * Time: 23:40
 */
@Entity
@Table(name = "mails")
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "mail_from", length = 30)
    private String mailFrom;
    @Column(name = "mail_to",  length = 30)
    private String mailTo;
    @Column(name = "subject",  length = 30)
    private String subject;
    @Column(name = "body",  length = 255)
    private String body;
    @Column(name = "date")
    private Date date;
    @Column(name = "is_read")
    private Boolean read;
    @Column(name = "mail_box_owner_id")
    private int mailBoxOwnerId;
    @OneToOne
    @PrimaryKeyJoinColumn
    private FolderEntity folder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public int getMailBoxOwnerId() {
        return mailBoxOwnerId;
    }

    public void setMailBoxOwnerId(int mailBoxOwnerId) {
        this.mailBoxOwnerId = mailBoxOwnerId;
    }

    public FolderEntity getFolder() {
        return folder;
    }

    public void setFolder(FolderEntity folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "id=" + id +
                ", mailFrom='" + mailFrom + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", read=" + read +
                ", mailBoxOwnerId=" + mailBoxOwnerId +
                ", folder=" + folder +
                '}';
    }
}

