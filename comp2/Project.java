public class Project {
    private String name;
    private Type type;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "name= " + name + ", type= " + type+ "\n";
    }
    
}
