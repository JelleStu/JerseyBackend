package ticketsystem.service.model.view;


public class TeamView {
    private int id;

    private String name;

    public TeamView() {
    }

    public TeamView(int id, String name) {
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
