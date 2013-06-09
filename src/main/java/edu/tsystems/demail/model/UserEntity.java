package edu.tsystems.demail.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 07.06.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="users")
@NamedQuery(name = "UserEntity.getAll", query = "SELECT u FROM UserEntity u")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "first_name", length = 31)
    @NotNull
    private String first_name;
    @Column (name = "last_name", length = 30)
    private String lname;
    @Column (name = "mid_name", length = 30)
    private String mname;
    @Column (name = "phone", length = 30)
    private String phone;
    @Column (name = "pass", length = 30)
    private String pass;

    public UserEntity(int id) {
        this.id = id;
    }

    public UserEntity(String first_name, String lname, String mname, String phone, String pass) {
        this.first_name = first_name;
        this.lname = lname;
        this.mname = mname;
        this.phone = phone;
        this.pass = pass;
    }

    public UserEntity() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String name) {
        this.first_name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + first_name + '\'' +
                ", lname='" + lname + '\'' +
                ", mname='" + mname + '\'' +
                ", phone='" + phone + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

