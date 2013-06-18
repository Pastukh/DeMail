package edu.tsystems.demail.DTO;

/**
 * Author: Ivan Pastukh
 * Date: 17.06.13
 * Time: 13:14
 */
public class FolderDTO {
    private String name;
    private Boolean systemFolder;
    private int id;
    private int userId;
    private int mailBoxId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSystemFolder() {
        return systemFolder;
    }

    public void setSystemFolder(Boolean systemFolder) {
        this.systemFolder = systemFolder;
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

    public int getMailBoxId() {
        return mailBoxId;
    }

    public void setMailBoxId(int mailBoxId) {
        this.mailBoxId = mailBoxId;
    }
}
