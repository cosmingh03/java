/**
 * Represents a project in the project assignment system.
 * Projects have a name and type.
 */
public class Project {
    /** The name of the project. */
    private String name;
    /** The type of the project. */
    private Type type;

    /**
     * Default constructor for Project class.
     */
    public Project() {
    }

    /**
     * Creates a new project with specified name.
     * 
     * @param name The name of the project
     */
    public Project(String name) {
        this.name = name;
    }

    /**
     * Creates a new project with specified name and type.
     * 
     * @param name The name of the project
     * @param type The type of the project
     */
    public Project(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the name of the project.
     * 
     * @return The project's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the project.
     * 
     * @return The project's type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the name of the project.
     * 
     * @param name The new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the project.
     * 
     * @param type The new type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the project.
     * 
     * @return A string containing the project's name and type
     */
    @Override
    public String toString() {
        return "name= " + name + ", type= " + type + "\n";
    }

    /**
     * Checks if this project is equal to another object.
     * Projects are considered equal if they have the same name.
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Project project = (Project) obj;
        if (name != null) {
            return name.equals(project.getName());
        } else {
            return false;
        }
    }

    /**
     * Generates a hash code for this project.
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        if (name != null) {
            return name.hashCode();
        } else {
            return 0;
        }
    }
}
