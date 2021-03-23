package ticketsystem.service.model.data;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
public class RoleDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="increment", strategy = "increment")
    private int RoleID;

    @Column(name = "NAME")
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(int roleID, String name) {
        RoleID = roleID;
        this.name = name;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
