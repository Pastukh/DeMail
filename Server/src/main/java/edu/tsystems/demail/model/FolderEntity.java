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
    @NamedQuery(name = "FolderEntity.getFolderByName",
                query = "SELECT f FROM FolderEntity f WHERE f.mailBox = :mailBox AND f.name = :name"),
    @NamedQuery(name = "FolderEntity.getFoldersByUserId",
            query = "SELECT f FROM FolderEntity f WHERE f.mailBox.userId = :userId"),
    @NamedQuery(name = "FolderEntity.getFolderByNameAndUserId",
            query = "SELECT f FROM FolderEntity f WHERE f.name = :name AND f.mailBox.userId = :userId")
})
public class FolderEntity extends BaseEntity {
    @Column(name = "name",  length = 30)
    private String name;
    @Column(name = "is_system")
    private Boolean system;
    @ManyToOne
    @JoinColumn(name = "mailBox")
    private MailBoxEntity mailBox;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
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
