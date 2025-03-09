/**
 * Represents a student in the project assignment system.
 * Students can have acceptable projects and can be assigned one project.
 * Extends the Person class.
 */
public class Student extends Person {
    /** The projects that are acceptable to the student. */
    private Project[] acceptableProjects;
    /** The project assigned to the student. */
    private Project assignedProject;
    /** The student's registration number. */
    private String registration;

    /**
     * Default constructor for Student class.
     */
    public Student() {
    }

    /**
     * Creates a new student with specified name and registration number.
     * 
     * @param name         The name of the student
     * @param registration The registration number of the student
     */
    public Student(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }

    /**
     * Creates a new student with specified name.
     * 
     * @param name The name of the student
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * Creates a new student with specified name and acceptable projects.
     * 
     * @param name               The name of the student
     * @param acceptableProjects Array of projects acceptable to the student
     */
    public Student(String name, Project[] acceptableProjects) {
        this.name = name;
        this.acceptableProjects = acceptableProjects;
    }

    /**
     * Creates a new student with specified name, acceptable projects, and assigned
     * project.
     * 
     * @param name               The name of the student
     * @param acceptableProjects Array of projects acceptable to the student
     * @param assignedProject    The project assigned to the student
     */
    public Student(String name, Project[] acceptableProjects, Project assignedProject) {
        this.name = name;
        this.acceptableProjects = acceptableProjects;
        this.assignedProject = assignedProject;
    }

    /**
     * Gets the registration number of the student.
     * 
     * @return The student's registration number
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * Sets the registration number of the student.
     * 
     * @param registration The new registration number
     */
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    /**
     * Gets the array of projects acceptable to the student.
     * 
     * @return Array of acceptable projects
     */
    public Project[] getAcceptableProjects() {
        return acceptableProjects;
    }

    /**
     * Gets the project assigned to the student.
     * 
     * @return The assigned project
     */
    public Project getAssignedProject() {
        return assignedProject;
    }

    /**
     * Checks if a project is acceptable to the student.
     * 
     * @param project The project to check
     * @return true if the project is acceptable, false otherwise
     */
    public boolean isAcceptableProject(Project project) {
        for (Project acceptableProject : acceptableProjects) {
            if (acceptableProject.equals(project)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the array of projects acceptable to the student.
     * 
     * @param acceptableProjects The new array of acceptable projects
     */
    public void setAcceptableProjects(Project[] acceptableProjects) {
        this.acceptableProjects = acceptableProjects;
    }

    /**
     * Assigns a project to the student if it is in their list of acceptable
     * projects.
     * 
     * @param assignedProject The project to assign
     */
    public void assignProject(Project assignedProject) {
        for (Project project : acceptableProjects) {
            if (project.equals(assignedProject)) {
                this.assignedProject = assignedProject;
                break;
            }
        }
    }

    /**
     * Adds a project to the student's list of acceptable projects.
     * 
     * @param project The project to add
     */
    public void addAcceptableProject(Project project) {
        if (acceptableProjects == null) {
            acceptableProjects = new Project[1];
            acceptableProjects[0] = project;
        } else {
            Project[] temp = new Project[acceptableProjects.length + 1];
            System.arraycopy(acceptableProjects, 0, temp, 0, acceptableProjects.length);
            temp[acceptableProjects.length] = project;
            acceptableProjects = temp;
        }
    }

    /**
     * Returns a string representation of the student.
     * 
     * @return A string containing the student's name and acceptable projects
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("name= ");
        result.append(name);
        result.append("\n");
        result.append("acceptableProjects= ");
        if (acceptableProjects != null) {
            for (int i = 0; i < acceptableProjects.length; i++) {
                result.append(acceptableProjects[i].getName());
                if (i != acceptableProjects.length - 1) {
                    result.append(", ");
                }
            }
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Checks if this student is equal to another object.
     * Students are considered equal if they have the same registration number or
     * name.
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

        Student student = (Student) obj;

        if (registration != null) {
            return registration.equals(student.registration);
        }

        if (name != null) {
            return name.equals(student.getName());
        } else {
            return false;
        }
    }

    /**
     * Generates a hash code for this student.
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        if (registration != null) {
            return registration.hashCode();
        } else if (name != null) {
            return name.hashCode();
        } else {
            return 0;
        }
    }
}
