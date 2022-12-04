package Project3;

import java.util.Arrays;

/**
 *
 * @author yaw
 */
public class GraphToolBox {

    public static boolean isVC(int[][] graphToBeTested) {


        return null;
    }

    // return an array containing the vertex numbers of an optimal VC.
    public static int[] exactVC(Graph inputGraph) {


        return null;
    }
    
    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {
        int[] vertexNumbers;

        int[][] originalGraph = inputGraph.getGraph(); //adjacency matrix
        int numVert = originalGraph.length;

        //testing stuff
        System.out.println(Arrays.toString(originalGraph[0]));
        System.out.println(Arrays.toString(originalGraph[1]));
        System.out.println(originalGraph[0].length);
        System.out.println(originalGraph[0][0]);

        System.out.println("Is the original graph a vertex cover?");
        System.out.println(isVC(originalGraph));

        //actual logic
        int[][] tempGraph = originalGraph;
        //gonna randomly remove a row and check if it is a vertex cover
        for (int i = 0; i < originalGraph.length; i++) {
            //if (isVC(tempGraph)) {


                //return vertexNumbers;
            //}
        }

        for (int i = 0; i < originalGraph.length; i++) { //number of rows
            for (int j = 0; j < originalGraph[i].length; j++) { //number of columns in a row (neighbors)

            }
        }



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
        System.out.println("All vertices of the vertex cover:");
        System.out.println(inexactVCAnswer);
    }

}






