package Project3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author yaw
 */
public class GraphToolBox {

    //Should be working, gotta test it some more
    public static boolean isVC(int[][] originGraph, ArrayList<Integer> listOfIgnoredVertices) {
        int graphSize = originGraph.length;

        //gets size
        boolean visited[] = new boolean[graphSize]; //gets size

        //sets all to false by default
        for (int i = 0; i < graphSize; i++) {
            visited[i] = false;
        }

        //sets each vertice and neighbors true unless it is to be ignored
        for (int u = 0; u < graphSize; u++) {
            if (visited[u] == false && !listOfIgnoredVertices.contains(u)) {
                for (int j = 0; j < originGraph[u].length; j++) {
                    if (originGraph[u] != null) {
                        visited[originGraph[u][j]] = true;
                        visited[u] = true;
                    }
                }
            }
        }

        //checks if there are any false in visited, if so, it is not a vertex cover
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                return false;
            }
        }
        //if it passes everything, it is a vertex cover
        return true;
    }

    // return an array containing the vertex numbers of an optimal VC.
    public static int[] exactVC(Graph inputGraph) {

        return null;
    }
    
    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {


        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix

        ArrayList<Integer> listOfIgnoredVertices = new ArrayList<Integer>();

        //gets smallest vertex cover by removing node 1 and so forth until it is not a vertex cover
        int count = 0;
        for (int i = 0; i < originalGraph.length; i++) {
                listOfIgnoredVertices.add(i);
                boolean isVertexCover = isVC(originalGraph, listOfIgnoredVertices);
                count++;
                if (!isVertexCover) {
                    break;
                }
            }

        ArrayList<Integer> ALvertexVC = new ArrayList<Integer>();

        Integer[] vertexVC = new Integer[originalGraph.length - listOfIgnoredVertices.size()];

        for (int i = 0; i < originalGraph.length; i++) {
            if (!listOfIgnoredVertices.contains(i)) {
                ALvertexVC.add(i);
            }
        }

        int[] arr = ALvertexVC.stream().mapToInt(i -> i).toArray();

        return arr;
    }
    
    // return an array containing the vertex numbers of an optimal IS.
    public static int[] optimalIS(Graph inputGraph) {
        return null;
    }
    
    // return (in polynomial time) an array containing the vertex numbers of a IS.
    public static int[] inexactIS(Graph inputGraph) {
        return null;
    }

    //Running the stuff down here


    public static void main(String[] args) {

        Graph graph1 = new Graph("graph1.txt");

        System.out.println("------------Exact Vertex Cover------------");
        //To be implemented
        System.out.println("-------------------------------------------");

        System.out.println("------------Inexact Vertex Cover------------");
        int[] inexactVCAnswer = inexactVC(graph1);
        System.out.println("List of vertices in vertex cover:");
        System.out.println(Arrays.toString(inexactVCAnswer));
        System.out.println("Number of vertices in the inexact vertex cover: " + inexactVCAnswer.length);
        System.out.println("-------------------------------------------");
    }

}






