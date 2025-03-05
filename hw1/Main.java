public class Main {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            if (args.length < 2) {
                System.out.println("2 arguments needed");
                return;
            }
            
            int size = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            
            Graph graph = new Graph(size, k);
            graph.generateGraph();
            
            System.out.println("n=" + size + ", k=" + k);
            graph.print();

            long end = System.currentTimeMillis();
            
            System.out.println("execution time=" + (end-start)+"ms");


            
        
        } catch (NumberFormatException e) {
            System.out.println("2 int needed");
        }
    }
}
