package edu.tsystems.demail.model;

import javax.persistence.*;

/**
 * Author: Ivan Pastukh
 * Date: 13.06.13
 * Time: 23:26
 */
@Entity
@Table(name = "folders")
@NamedQueries({
    @NamedQuery(name = "FolderEntity.getFoldersByUserId",
            query = "SELECT f FROM FolderEntity f WHERE f.mailBox.userId = :userId"),
    @NamedQuery(name = "FolderEntity.getFolderByNameAndUserId",
            query = "SELECT f FROM FolderEntity f WHERE f.name = :name AND f.mailBox.userId = :userId")
})
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",  length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "mailBox")
    private MailBoxEntity mailBox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MailBoxEntity getMailBox() {
        return mailBox;
    }

    public void setMailBox(MailBoxEntity mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public String toString() {
        return "FolderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mailBox=" + mailBox +
                '}';
    }
}
