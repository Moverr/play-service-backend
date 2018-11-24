package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

/**
 * Created by peterszatmary on 6.10.2015.
 */
@Entity
public class Friend {

    @Id
    private long id;
    @Column
    private String name;
    @Column
    private String surname;

    // mock
    public Friend(long id) {
        this.id = id;
        this.name = "name" + new Random().nextInt(1234);
        this.surname = "surname" + new Random().nextInt(1234);
    }

    public Friend() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
