package edu.tsystems.demail.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 13.06.13
 * Time: 23:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "mails")
public class MailEntity {
    @Id
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





}

