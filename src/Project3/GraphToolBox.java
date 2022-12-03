package Project3;

/**
 *
 * @author yaw
 */
public class GraphToolBox {
    // return an array containing the vertex numbers of an optimal VC.
    public static int[] exactVC(Graph inputGraph) {


        return null;
    }
    
    // return (in polynomial time) an array containing the vertex numbers of a VC.
    public static int[] inexactVC(Graph inputGraph) {
        int[] vertexNumbers;

        int[][] workingGraph = inputGraph.getGraph();

        for (int i = 0; i < 5; i++) {
            System.out.println(workingGraph[i][0]);
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

        System.out.println(inexactVCAnswer);
    }

}






