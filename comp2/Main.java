public class Main {

    public static void main(String[] args) {
        Project P1 = new Project("P1", Type.Theoretical);
        Project P2 = new Project("P2", Type.Practical);
        Project P3 = new Project("P3", Type.Theoretical);
        Project P4 = new Project("P4", Type.Practical);


        Student S1 = new Student("S1", new Project[]{P1, P2});
        Student S2 = new Student("S2", new Project[]{P1, P3});
        Student S3 = new Student("S3", new Project[]{P3, P4});
        Student S4 = new Student("S4", new Project[]{P1, P4});

        Assigment assigment= new Assigment(new Student[]{S1, S2, S3, S4}, new Project[]{P1, P2, P3, P4});
        assigment.Assign();

        System.out.println("students:");
        System.out.println(S1.toString());
        System.out.println(S2.toString()); 

        System.out.println("projects:"); 
        System.out.println(P1.toString()); 
        System.out.println(P2.toString());
        
        System.out.println(assigment.toString());

       
    }
}
