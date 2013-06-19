package edu.tsystems.demail.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Ivan Pastukh
 * Date: 19.06.13
 * Time: 2:16
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
