package Project3;

import java.util.Arrays;

/**
 *
 * @author yaw
 */
public class GraphToolBox {

    //Should be working, gotta test it some more
    public static boolean isVC(int[][] originGraph, int startingVertex) {
        int graphSize = originGraph.length;

        //gets size
        boolean visited[] = new boolean[graphSize]; //gets size

        //sets all to false by default
        for (int i = 0; i < graphSize; i++) {
            visited[i] = false;
        }
        //checks all edges and marks vertex if visited
        //working code below

        /*for (int u = 0; u < graphSize; u++) {
            if (visited[u] == false) {
                for (int j = 0; j < originGraph[u].length; j++) {
                    if (originGraph[u] != null) {
                        visited[originGraph[u][j]] = true;
                        visited[u] = true;
                    }
                }
            }
        }*/

        for (int u = startingVertex; u < graphSize - startingVertex; u++) {
            if (visited[u] == false) {
                for (int j = 0; j < originGraph[u].length; j++) {
                    if (originGraph[u] != null) {
                        visited[originGraph[u][j]] = true;
                        visited[u] = true;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(visited));

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
        int numVert = originalGraph.length;

        //testing stuff
        System.out.println(Arrays.toString(originalGraph[0]));
        System.out.println(originalGraph[0].length);
        System.out.println(Arrays.toString(originalGraph[1]));
        System.out.println(originalGraph[1].length);
        System.out.println(originalGraph[0][0]);

        System.out.println("Is the original graph a vertex cover?");
        System.out.println(isVC(originalGraph, 0));
        System.out.println("Reducing the graph to see if the next is a cover");

        int[][] tempGraph = originalGraph;

        System.out.println("Trying to see if this will fail");
        System.out.println(isVC(originalGraph, 7));

        //System.out.println(Arrays.toString(tempGraph[0]));
        //System.out.println(Arrays.toString(tempGraph[1]));

        //actual logic



        //checking for base vertex cover functionality
        //gonna randomly remove a row and check if it is a vertex cover
        //DONT REMOVE A ROW, just set it to be empty
        /*for (int i = 0; i < originalGraph.length; i++) {
            if (isVC(tempGraph) == true) {
                int[] vertexNumbers = new int[tempGraph.length];
                for (int j = 0; j < tempGraph.length; j++) {
                    vertexNumbers[j] = tempGraph[i][j];
                }

                return vertexNumbers;
            }
        }*/

        return null;
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

        int[] inexactVCAnswer = inexactVC(graph1);
        System.out.println("Number of vertices in the inexact vertex cover: ");
        System.out.println(inexactVCAnswer.length);
    }

}






