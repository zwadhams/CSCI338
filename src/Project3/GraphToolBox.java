package Project3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author yaw
 */
public class GraphToolBox {

    //function to determine if a graph (minus some vertices specified in listOfIgnoredVertices) is a vertex cover
    public static boolean isVC(int[][] originGraph, ArrayList<Integer> listOfIgnoredVertices) {
        int graphSize = originGraph.length;

        //gets size
        boolean visited[] = new boolean[graphSize]; //gets size

        //sets all to false by default
        for (int i = 0; i < graphSize; i++) { //O(n)
            visited[i] = false;
        }

        //sets each vertex and neighbors true unless it is to be ignored
        for (int u = 0; u < graphSize; u++) { //O(n)
            if (!listOfIgnoredVertices.contains(u)) {
                for (int j = 0; j < originGraph[u].length; j++) { //O(n)
                    if (originGraph[u] != null) {
                        visited[originGraph[u][j]] = true;
                        visited[u] = true;
                    }
                }
            }
        }

        //checks if there are any false in visited, if so, it is not a vertex cover
        for (int i = 0; i < visited.length; i++) { //O(n)
            if (visited[i] == false) {
                return false;
            }
        }

        //if it gets to here, it is a vertex cover
        return true;
    }

    //needs work
    public static boolean isIS(int[][] originGraph, ArrayList<Integer> listOfIgnoredVertices) {
        int graphSize = originGraph.length;

        //logic to determine if two vertices are connected
        int currentVertex;
        for (int i = 0; i < graphSize; i++) { //each vertex
            if (!listOfIgnoredVertices.contains(i)) {
                currentVertex = i;
                for (int j = 0; j < graphSize; j++) {
                    if (currentVertex != j && !listOfIgnoredVertices.contains(j)) {
                        for (int k = 0; k < originGraph[currentVertex].length; k++) {
                            if (originGraph[j][k] == currentVertex) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        //if it gets to here, it is an independent set
        return true;
    }

    // return an array containing the vertex numbers of an optimal VC.
    public static int[] exactVC(Graph inputGraph) {
        long startTime = System.nanoTime();

        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix

        ArrayList<Integer> listOfIgnoredVertices = new ArrayList<Integer>();

        //gets smallest vertex cover by removing node 1 and so forth until it is not a vertex cover
        int brokeOn = 0;
        for (int i = 0; i < originalGraph.length; i++) {
            listOfIgnoredVertices.add(i);
            boolean isVertexCover = isVC(originalGraph, listOfIgnoredVertices);
            if (!isVertexCover) {
                brokeOn = i;
                break;
            }
        }

        ArrayList<Integer> ALvertexVC = new ArrayList<Integer>();

        for (int i = 0; i < originalGraph.length; i++) {
            if (!listOfIgnoredVertices.contains(i) || i == brokeOn) {
                ALvertexVC.add(i);
            }
        }

        int[] arr = ALvertexVC.stream().mapToInt(i -> i).toArray();

        long endTime = System.nanoTime();
        System.out.println("Took this long: " + (endTime-startTime));
        return arr;
    }

    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {

        long startTime = System.nanoTime();

        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix

        ArrayList<Integer> listOfIgnoredVertices = new ArrayList<Integer>();

        //gets smallest vertex cover by removing node 1 and so forth until it is not a vertex cover
        int brokeOn = 0;
        for (int i = 0; i < originalGraph.length; i++) {
            listOfIgnoredVertices.add(i);
            boolean isVertexCover = isVC(originalGraph, listOfIgnoredVertices);
            if (!isVertexCover) {
                brokeOn = i;
                break;
            }
        }

        ArrayList<Integer> ALvertexVC = new ArrayList<Integer>();

        for (int i = 0; i < originalGraph.length; i++) {
            if (!listOfIgnoredVertices.contains(i) || i == brokeOn) {
                ALvertexVC.add(i);
            }
        }

        int[] arr = ALvertexVC.stream().mapToInt(i -> i).toArray();


        long endTime = System.nanoTime();
        System.out.println("Took this long: " + (endTime-startTime));
        return arr;
    }

    // return an array containing the vertex numbers of an optimal IS.
    public static int[] optimalIS(Graph inputGraph) {
        //make it run for a super long by forcing a exponential time maybe?
        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix


        ArrayList<Integer> listOfIgnoredVertices = new ArrayList<Integer>();

        return null;
    }

    // return (in polynomial time) an array containing the vertex numbers of a IS.
    public static int[] inexactIS(Graph inputGraph) {

        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix

        ArrayList<Integer> listOfIgnoredVertices = new ArrayList<Integer>();

        //fill our ignoredVertices
        for (int i = 0; i < originalGraph.length; i++) {
            listOfIgnoredVertices.add(i);
        }

        //testing
        int brokeOn = 0;
        //gets largest independent by adding node 1 (by removing from ignored) and so forth until it is not an independent set
        for (int i = originalGraph.length - 1; i > -1; i--) {
            listOfIgnoredVertices.remove(i);
            boolean isIndependentSet = isIS(originalGraph, listOfIgnoredVertices);
            if (!isIndependentSet) {
                brokeOn = i;
                break;
            }
        }

        ArrayList<Integer> ALvertexIS = new ArrayList<Integer>();

        for (int i = 0; i < originalGraph.length; i++) {
            if (!listOfIgnoredVertices.contains(i) && i != brokeOn) {
                ALvertexIS.add(i);
            }
        }

        int[] arr = ALvertexIS.stream().mapToInt(i -> i).toArray();

        return arr;
    }

    //Running the stuff down here
    public static void main(String[] args) {

        //simple testing
        Graph simpleGraph = new Graph("simple.txt");
        Graph simple10 = new Graph("simple10.txt");

        /*System.out.println("Simple graph testing");
        System.out.println("---vertex simple---");
        int [] simpleVC = inexactVC(simpleGraph);
        System.out.println(Arrays.toString(simpleVC));
        System.out.println("---ind set simple---");
        int [] simpleIS = inexactIS(simpleGraph);
        System.out.println(Arrays.toString(simpleIS));

         */

        Graph graph1 = new Graph("graph1.txt");

        System.out.println("------------Exact Vertex Cover------------");
        int[] exactVCAnswer = exactVC(simple10);
        System.out.println("List of vertices in vertex cover:");
        System.out.println(Arrays.toString(exactVCAnswer));
        System.out.println("Number of vertices in the inexact vertex cover: " + exactVCAnswer.length);
        System.out.println("-------------------------------------------");

        System.out.println("------------Inexact Vertex Cover------------");
        int[] inexactVCAnswer = inexactVC(simple10);
        System.out.println("List of vertices in vertex cover:");
        System.out.println(Arrays.toString(inexactVCAnswer));
        System.out.println("Number of vertices in the inexact vertex cover: " + inexactVCAnswer.length);
        System.out.println("-------------------------------------------");

        System.out.println("----------Optimal Independent Set----------");
        //To be implemented
        int[] optimalISAnswer = inexactVC(simpleGraph);
        System.out.println(Arrays.toString(optimalISAnswer));
        System.out.println("-------------------------------------------");

        System.out.println("----------Inexact Independent Set----------");
        int[] inexactISAnswer = inexactIS(simpleGraph);
        System.out.println("List of vertices in independent set:");
        System.out.println(Arrays.toString(inexactISAnswer));
        System.out.println("Number of vertices in the inexact independent set: " + inexactISAnswer.length);
        System.out.println("-------------------------------------------");
    }

}






