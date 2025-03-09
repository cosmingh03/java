public class Main {

    public static void main(String[] args) {
        Project P1 = new Project("P1", Type.Theoretical);
        Project P2 = new Project("P2", Type.Practical);
        Project P3 = new Project("P3", Type.Theoretical);
        Project P4 = new Project("P4", Type.Practical);

        Student S1 = new Student("S1", new Project[] { P1, P2 });
        Student S2 = new Student("S2", new Project[] { P1, P3 });
        Student S3 = new Student("S3", new Project[] { P3, P4 });
        Student S4 = new Student("S4", new Project[] { P1, P4 });

        Teacher T1 = new Teacher("T1", new Project[] { P1, P2 });
        Teacher T2 = new Teacher("T2", new Project[] { P3, P4 });
        Teacher T3 = new Teacher("T3", new Project[] { P1, P4 });
        Teacher T4 = new Teacher("T4", new Project[] { P2, P3 });

        Problem P = new Problem(new Student[] { S1, S2, S3, S4 }, new Project[] { P1, P2, P3, P4 },
                new Teacher[] { T1, T2, T3, T4 });
        System.out.println(P.toString());
        P.Solve();
        System.out.println(P.printSolution());

    }
}
