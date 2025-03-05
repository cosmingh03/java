

public class Student {
    private String name;
    private Project[] acceptableProjects;
    private Project assignedProject;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Project[] acceptableProjects) {
        this.name = name;
        this.acceptableProjects = acceptableProjects;

    }

    public Student (String name, Project[] acceptableProjects, Project assignedProject) {
        this.name = name;
        this.acceptableProjects = acceptableProjects;
        this.assignedProject = assignedProject;
    }

    public String getName() {
        return name;
    }

    public Project[] getAcceptableProjects() {
        return acceptableProjects;
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public boolean isAcceptableProject(Project project) {
        for (Project acceptableProject : acceptableProjects) {
            if (acceptableProject.equals(project)) {
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcceptableProjects(Project[] acceptableProjects) {
        this.acceptableProjects = acceptableProjects;
    }

    public void assignProject(Project assignedProject) {
        for (Project project : acceptableProjects) {
            if (project.equals(assignedProject)) {
                this.assignedProject = assignedProject;
                break;
            }
        }

    }

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("name= ");
        result.append(name);
        result.append("\n");
        result.append("acceptableProjects= ");
        if (acceptableProjects != null) {
            for (int i=0; i<acceptableProjects.length; i++) {
                result.append(acceptableProjects[i].getName());
                if (i != acceptableProjects.length-1) {
                    result.append(", ");
                }
            }
        }
        result.append("\n");
        return result.toString();
        

    }



}
