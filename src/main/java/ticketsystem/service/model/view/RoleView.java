package ticketsystem.service.model.view;

public class RoleView {
    private int RoleID;

    private String name;

    public RoleView() {
    }

    public RoleView(int roleID, String name) {
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
