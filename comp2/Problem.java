/**
 * Represents a project assignment problem.
 * Contains collections of students, projects, and teachers.
 * Provides functionality to manage these entities and solve the assignment
 * problem.
 */
public class Problem {
    /** Array of students in the problem. */
    private Student[] students;
    /** Array of projects in the problem. */
    private Project[] projects;
    /** Array of teachers in the problem. */
    private Teacher[] teachers;

    /**
     * Default constructor for Problem class.
     */
    public Problem() {
    }

    /**
     * Creates a new problem with specified students, projects, and teachers.
     * 
     * @param students Array of students
     * @param projects Array of projects
     * @param teachers Array of teachers
     */
    public Problem(Student[] students, Project[] projects, Teacher[] teachers) {
        this.students = students;
        this.projects = projects;
        this.teachers = teachers;
    }

    /**
     * Adds a student to the problem if they don't already exist.
     * 
     * @param newStudent The student to add
     */
    public void addStudent(Student newStudent) {
        // Check if student already exists
        if (students != null) {
            for (Student student : students) {
                if (student.equals(newStudent)) {
                    return; // Student already exists, don't add
                }
            }
        }

        // Add new student
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

    /**
     * Adds a project to the problem if it doesn't already exist.
     * 
     * @param newProject The project to add
     */
    public void addProject(Project newProject) {
        // Check if project already exists
        if (projects != null) {
            for (Project project : projects) {
                if (project.equals(newProject)) {
                    return; // Project already exists, don't add
                }
            }
        }

        // Add new project
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

    /**
     * Adds a teacher to the problem if they don't already exist.
     * 
     * @param newTeacher The teacher to add
     */
    public void addTeacher(Teacher newTeacher) {
        // Check if teacher already exists
        if (teachers != null) {
            for (Teacher teacher : teachers) {
                if (teacher.equals(newTeacher)) {
                    return; // Teacher already exists, don't add
                }
            }
        }

        // Add new teacher
        if (this.teachers == null) {
            this.teachers = new Teacher[1];
            this.teachers[0] = newTeacher;
        } else {
            Teacher[] temp = new Teacher[this.teachers.length + 1];
            System.arraycopy(this.teachers, 0, temp, 0, this.teachers.length);
            temp[this.teachers.length] = newTeacher;
            this.teachers = temp;
        }
    }

    /**
     * Gets the array of students in the problem.
     * 
     * @return Array of students
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * Gets the array of projects in the problem.
     * 
     * @return Array of projects
     */
    public Project[] getProject() {
        return projects;
    }

    /**
     * Gets the array of teachers in the problem.
     * 
     * @return Array of teachers
     */
    public Teacher[] getTeachers() {
        return teachers;
    }

    /**
     * Sets the array of students in the problem.
     * 
     * @param student The new array of students
     */
    public void setStudent(Student[] student) {
        this.students = student;
    }

    /**
     * Sets the array of projects in the problem.
     * 
     * @param project The new array of projects
     */
    public void setProject(Project[] project) {
        this.projects = project;
    }

    /**
     * Sets the array of teachers in the problem.
     * 
     * @param teacher The new array of teachers
     */
    public void setTeacher(Teacher[] teacher) {
        this.teachers = teacher;
    }

    /**
     * Returns a string representation of the problem.
     * 
     * @return A string describing the problem
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("students preferences: \n");
        for (Student student : students) {
            result.append(student.toString());
        }
        result.append("teaches proposed projects: \n");
        for (Teacher teacher : teachers) {
            result.append(teacher.toString());
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * Gets all persons (students and teachers) in the problem.
     * 
     * @return Array of persons
     */
    public Person[] getPersons() {
        Person[] persons = new Person[students.length + teachers.length];
        System.arraycopy(students, 0, persons, 0, students.length);
        System.arraycopy(teachers, 0, persons, students.length, teachers.length);
        return persons;
    }

    /**
     * Students with fewer acceptable project options are prioritized in the
     * allocation
     * to ensure a more fair distribution of projects.
     */
    public void Solve() {
        // Check if there is no null/0
        if (students == null || projects == null || students.length == 0 || projects.length == 0) {
            return;
        }

        boolean[] assigned = new boolean[projects.length];

        Student[] sortedStudents = new Student[students.length];
        System.arraycopy(students, 0, sortedStudents, 0, students.length);

        // Sort students by number of acceptable projects (ascending)
        for (int i = 0; i < sortedStudents.length - 1; i++) {
            for (int j = i + 1; j < sortedStudents.length; j++) {

                int countI = 0;
                int countJ = 0;

                // Sort by ascending order of choices (fewer choices first)
                if (countI > countJ) {
                    Student temp = sortedStudents[i];
                    sortedStudents[i] = sortedStudents[j];
                    sortedStudents[j] = temp;
                }
            }
        }

        // Assign projects to students in priority order
        for (Student student : sortedStudents) {

            boolean studentAssigned = false;

            // First try to assign from their acceptable projects list
            for (Project acceptableProject : student.getAcceptableProjects()) {
                // Find this project in our master projects list
                for (int j = 0; j < projects.length; j++) {
                    if (projects[j].equals(acceptableProject) && !assigned[j]) {
                        student.assignProject(projects[j]);
                        assigned[j] = true;
                        studentAssigned = true;
                        break;
                    }
                }

                if (studentAssigned) {
                    break;
                }
            }

            // Optional: Log if a student couldn't get assigned
            if (!studentAssigned) {
                System.out.println("not assigned: " + student.getName());
            }
        }
    }

    /**
     * Returns a string representation of the solution.
     * 
     * @return A string describing the solution
     */
    public String printSolution() {
        StringBuilder result = new StringBuilder();

        result.append("[");
        if (students != null) {

            for (int i = 0; i < students.length; i++) {
                result.append("(");
                result.append(students[i].getName());
                result.append(",");

                if (students[i].getAssignedProject() == null) {
                    result.append("null");
                } else {
                    result.append(students[i].getAssignedProject().getName());
                }

                result.append(")");

                if (i != students.length - 1) {
                    result.append(",");
                }
            }
        }

        result.append("]\n");

        return result.toString();
    }
}
