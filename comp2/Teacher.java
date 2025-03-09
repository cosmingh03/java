import java.util.Arrays;

/**
 * Represents a teacher in the project assignment system.
 * Teachers can propose projects for students.
 * Extends the Person class.
 */
public class Teacher extends Person {
    /** The projects proposed by the teacher. */
    private Project[] proposed;

    /**
     * Default constructor for Teacher class.
     */
    public Teacher() {
    }

    /**
     * Creates a new teacher with specified name.
     * 
     * @param name The name of the teacher
     */
    public Teacher(String name) {
        this.name = name;
    }

    /**
     * Creates a new teacher with specified name and proposed projects.
     * 
     * @param name     The name of the teacher
     * @param proposed Array of projects proposed by the teacher
     */
    public Teacher(String name, Project[] proposed) {
        this.name = name;
        this.proposed = proposed;
    }

    /**
     * Gets the array of projects proposed by the teacher.
     * 
     * @return Array of proposed projects
     */
    public Project[] getProposed() {
        return proposed;
    }

    /**
     * Sets the array of projects proposed by the teacher.
     * 
     * @param proposed The new array of proposed projects
     */
    public void setProposed(Project[] proposed) {
        this.proposed = proposed;
    }

    /**
     * Checks if a project is proposed by the teacher.
     * 
     * @param project The project to check
     * @return true if the project is proposed, false otherwise
     */
    public boolean isProposed(Project project) {
        for (Project p : proposed) {
            if (p.equals(project)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a project to the teacher's list of proposed projects.
     * 
     * @param project The project to add
     */
    public void addProposed(Project project) {
        Project[] newProposed = new Project[proposed.length + 1];
        System.arraycopy(proposed, 0, newProposed, 0, proposed.length);
        newProposed[proposed.length] = project;
        proposed = newProposed;
    }

    /**
     * Returns a string representation of the teacher.
     * 
     * @return A string containing the teacher's name and proposed projects
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nProposed Projects: " + Arrays.toString(proposed);
    }

    /**
     * Checks if this teacher is equal to another object.
     * Teachers are considered equal if they have the same name.
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

        Teacher teacher = (Teacher) obj;
        if (name != null) {
            return name.equals(teacher.getName());
        } else {
            return false;
        }
    }

    /**
     * Generates a hash code for this teacher.
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
