import java.util.Random;
public class Graph {
    private final int[][] matrix;
    private final int size;
    private final int k;
    private int[] cliqueVertices; 
    private int[] stableSetVertices; 

    public Graph(int n, int k) {
        this.size = n;
        this.matrix = new int[size][size];
        this.k = k;
        this.cliqueVertices = new int[0];
        this.stableSetVertices = new int[0];
    }

    public void generateGraph() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }

        if (size < 2*this.k) {
            generateRandom();
        }
        else {
            int[] clique = selectVertices();
            int[] stable = selectVerticesExclud(clique);
            
            this.cliqueVertices = clique;
            this.stableSetVertices = stable;
            
            Clique(clique);
            stableSet(stable);
            fillMatrix(clique, stable);
        }
    }

    private int[] selectVertices() {
        int[] vertices = new int[this.k];
        boolean[] used = new boolean[size];

        int count = 0;
        while (count < this.k) {
            Random random = new Random();
            int vertex = random.nextInt(size);
            if (!used[vertex]) {
                vertices[count] = vertex;
                used[vertex] = true;
                count++;
            }
        }

        return vertices;
    }

    private int[] selectVerticesExclud( int[] excluded) {
        int[] vertices = new int[this.k];
        boolean[] used = new boolean[size];

        for (int v : excluded) {
            used[v] = true;
        }

        int count = 0;
        while (count < this.k) {
            Random random = new Random();
            int vertex = random.nextInt(size);
            if (!used[vertex]) {
                vertices[count] = vertex;
                used[vertex] = true;
                count++;
            }
        }

        return vertices;
    }

    private void Clique(int[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                matrix[vertices[i]][vertices[j]] = 1;
                matrix[vertices[j]][vertices[i]] = 1;
            }
        }
    }

    private void stableSet(int[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i != j) {
                    matrix[vertices[i]][vertices[j]] = 0;
                    matrix[vertices[j]][vertices[i]] = 0;
                }
            }
        }
    }

    private void generateRandom() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                Random random = new Random();
                int edge = random.nextInt(2);
                matrix[i][j] = edge;
                matrix[j][i] = edge;
            }
        }
    }

    private void fillMatrix(int[] cliqueVertices, int[] stableVertices) {
        boolean[] isInClique = new boolean[size];
        boolean[] isInStableSet = new boolean[size];

        for (int v : cliqueVertices) {
            isInClique[v] = true;
        }

        for (int v : stableVertices) {
            isInStableSet[v] = true;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {

                if ((isInClique[i] && isInClique[j]) ||
                        (isInStableSet[i] && isInStableSet[j]) ){
                    continue;
                }

                Random random = new Random();
                int edge = random.nextInt(2);
                matrix[i][j] = edge;
                matrix[j][i] = edge;
            }
        }
    }

    private String pretty() {
        StringBuilder sb = new StringBuilder();
                
        sb.append("   "); 
        for (int j = 0; j < size; j++) {
            sb.append("[").append(j).append("] ");
        }
        sb.append("\n");
        
        for (int i = 0; i < size; i++) {
            sb.append("[").append(i).append("] ");
            


            for (int j = 0; j < size; j++) {

                boolean isCliqueEdge = false;
                boolean isStableEdge = false;

                for (int v : cliqueVertices) {
                    if (v == i) {
                        for (int u : cliqueVertices) {
                            if (u == j) {
                                isCliqueEdge = true;
                                break;
                            }
                        }
                        break;
                    }
                }

                for (int v : stableSetVertices) {
                    if (v == i) {
                        for (int u : stableSetVertices) {
                            if (u == j) {
                                isStableEdge = true;
                                break;
                            }
                        }
                        break;
                    }
                }
                
                if (matrix[i][j] == 1) {
                    if (isCliqueEdge) {
                        sb.append("\u25A0   "); 
                    } else {
                        sb.append("1   "); 
                    }
                } else {
                    if (isStableEdge && i != j) {
                        sb.append("\u25A1   "); 
                    } else {
                        sb.append("0   "); 
                    }
                }
            }
            sb.append("\n");
        }

        sb.append("where:\n\u25A0 - clique edge\n\u25A1 - stable set edge");
        sb.append("\nclique: ");
        sb.append("[");
        for (int i = 0; i < cliqueVertices.length; i++) {
            sb.append(cliqueVertices[i]);
            if (i < cliqueVertices.length - 1) {
            sb.append(",");
            }
        }
        sb.append("]");
        
        sb.append("\nstable set: ");
        sb.append("[");
        for (int i = 0; i < stableSetVertices.length; i++) {
            sb.append(stableSetVertices[i]);
            if (i < stableSetVertices.length - 1) {
            sb.append(",");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }

    private int getEdges() {
        int edges = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                edges += matrix[i][j];
            }
        }
        return edges;
    }
    private int minDegree() {
        int min = size;
        for (int i = 0; i < size; i++) {
            int degree = 0;
            for (int j = 0; j < size; j++) {
                degree += matrix[i][j];
            }
            if (degree < min) {
                min = degree;
            }
        }
        return min;
    }
    private int maxDegree() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            int degree = 0;
            for (int j = 0; j < size; j++) {
                degree += matrix[i][j];
            }
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }
    private int sumDegree() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int degree = 0;
            for (int j = 0; j < size; j++) {
                degree += matrix[i][j];
            }
            sum += degree;
        }
        return sum;
    }

    public void print() {
        if (size <= 1000) 
        {
            System.out.println(pretty());
        }
        System.out.println("m=" + getEdges());
        System.out.println("Δ(G)=" + maxDegree());
        System.out.println("δ(G)=" + minDegree());
        if (sumDegree() == 2 * getEdges()) {
            System.out.println("suma gradelor este 2*m");
        } else {
            System.out.println("suma gradelor nu este 2*m");
        }
  
    }




}