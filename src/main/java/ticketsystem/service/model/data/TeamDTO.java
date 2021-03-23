package ticketsystem.service.model.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TEAMS")
public class TeamDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;


    @Column(name = "NAME")
    private String name;

    public TeamDTO() {
    }

    public TeamDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
