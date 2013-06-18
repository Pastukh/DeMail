package edu.tsystems.demail.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 13.06.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "folders")
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",  length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "mailBox")
    private MailBoxEntity mailBox;
}
