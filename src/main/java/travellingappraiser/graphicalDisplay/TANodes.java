package travellingappraiser.graphicalDisplay;

import org.graphstream.graph.Graph;

/**
 * Created by darrick on 5/15/16.
 *
 * Travelling Appraiser Nodes:
 */
public class TANodes {

    private static float[][] nodes;  //Home is at row 0.  Rest must be consistent with intial user input order.

    private static int[] edges;

    public static void setNodeArray(double[][] verteces) {

        nodes = new float[verteces.length][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < verteces.length; j++) {
                nodes[j][i] = (float) verteces[j][i];
            }
        }

    }

    //takes a set of genes and constructs an array which describes each circuit.
    public static void setEdges(short[] genes, short[] routes) {
        edges = new int[genes.length+routes.length+1];

        int index = 0;
        int mod = 0;
        edges[index+mod++] = 0;

        for (short s:routes) {
            for (int i = 0; i < s; i++) {
                edges[index+mod+i] = genes[index+i];
            }
            index+=s;
            edges[index+mod++] = 0;
        }

    }

    public static void addNodes(Graph g) {
        for (int i = 0; i < nodes.length; i++) {
            g.addNode(""+i).addAttribute("xy", nodes[i][0],nodes[i][1]);  //breakpoint is to remember that we're not sure if this works.
        }
    }

    public static void updateEdges(Graph g) {

    }



}
