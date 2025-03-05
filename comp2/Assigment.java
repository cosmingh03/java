public class Assigment {
    private Student[] students;
    private Project[] projects;

    public Assigment() {
    }

    public Assigment(Student[] students, Project[] projects) {
        this.students = students;
        this.projects = projects;
    }

    public void addStudent(Student newStudent) {
        if (this.students == null) {
            this.students = new Student[1];
            this.students[0] = newStudent;
        } else {
            Student[] temp = new Student[this.students.length + 1];
            System.arraycopy(this.students, 0, temp, 0, this.students.length);
            temp[this.students.length] = newStudent;
            this.students = temp;
        }
    }

    public void addProject(Project newProject) {
        if (this.projects == null) {
            this.projects = new Project[1];
            this.projects[0] = newProject;
        } else {
            Project[] temp = new Project[this.projects.length + 1];
            System.arraycopy(this.projects, 0, temp, 0, this.projects.length);
            temp[this.projects.length] = newProject;
            this.projects = temp;
        }
    }

    public Student[] getStudents() {
        return students;
    }

    public Project[] getProject() {
        return projects;
    }

    public void setStudent(Student[] student) {
        this.students = student;
    }

    public void setProject(Project[] project) {
        this.projects = project;
    }

    public void Assign()
    {
        boolean[] assigned = new boolean[projects.length];

        for (Student student : students) {
            for (int j = 0; j < projects.length; j++) {
                if (student.isAcceptableProject(projects[j]) && !assigned[j]) {
                    student.assignProject(projects[j]);
                    assigned[j] = true;
                    break;
                }
            }
        }

                                                              
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        result.append("[");
        if (students != null) {
            
            for (int i = 0; i < students.length; i++) {
                result.append("(");
                result.append(students[i].getName());
                result.append(",");

                if(students[i].getAssignedProject() == null)
                {
                    result.append("null");
                }
                else
                {
                    result.append(students[i].getAssignedProject().getName());
                }
                
                result.append(")");
                
                if(i!=students.length-1)
                {
                    result.append(",");
                }
            }

        }
       
        result.append("]\n");
        
        return result.toString();
    }
}
