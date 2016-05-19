package travellingappraiser.graphicalDisplay;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import travellingappraiser.geneticAlgorithm.Tour;

import java.util.Arrays;

/**
 * Created by darrick on 5/15/16.
 *
 * Initializes, maintains, and displays a graph using a field "tour" shared by GARun.
 */
public class GraphStreamGraph {

    //fields for Graphical Display
    public Graph graph;
    private Viewer viewer;


    //fields for Graph G(V,E) computation
    private final float[][] nodes;  //Home is at row 0.  Rest must be consistent with intial user input order.
    private int[] edges;
    private Tour tour;  //points to a tour, "bestTour" in RunGA
        private short[] genes;
        private short[] routes;

    public GraphStreamGraph(double[][] locations, Tour tour) {
        this.tour = tour;

        nodes = new float[locations.length][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < locations.length; j++) {
                nodes[j][i] = (float) locations[j][i];
            }
        }
    }

    public void startGraph() {

        graph = new SingleGraph("Location Map");
        addNodes();
    }

    public void newViewer() {
        this.viewer = this.graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
    }

    //takes a set of genes and constructs an array which describes each circuit.
    private boolean setEdges() {
        updateGenes();

        int[] newEdges = new int[genes.length+routes.length+1];

        int index = 0;
        int mod = 0;
        newEdges[index+mod++] = 0;

        for (short s:routes) {
            for (int i = 0; i < s; i++) {
                newEdges[index+mod+i] = genes[index+i];
            }
            index+=s;
            newEdges[index+mod++] = 0;
        }

        if(Arrays.equals(newEdges,edges)) {return false;}
        else {
            edges = newEdges;
            return true;
        }

    }

    private void addNodes() {
        for (int i = 0; i < nodes.length; i++) {
            Node n = graph.addNode("" + i);
            n.addAttribute("xy", nodes[i][0],nodes[i][1]);  //breakpoint is to remember that we're not sure if this works.
        }
    }

    public void updateEdges() {
        if(setEdges()) {
            while (graph.getEdgeCount() > 0) {
                graph.removeEdge(0);
            }

            for (int i = 0; i < edges.length - 1; i++) {
                graph.addEdge("" + i, edges[i], edges[i + 1]);
            }
        }
    }

    private void updateGenes() {
        this.routes = this.tour.getRouteData();
        this.genes = this.tour.getPathing();


    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    /*
    private void setNodeArray(double[][] vertices) {

        nodes = new float[vertices.length][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < vertices.length; j++) {
                nodes[j][i] = (float) vertices[j][i];
            }
        }

    }
    */
    /*public void run() {

        startGraph();
        viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);
        viewer.disableAutoLayout();
        viewer.enableXYZfeedback(true);


        //have to try synchronization with Graphstreamgraphtest
        synchronized (graphLock) {
            while (!graphLock.isFinished()) {
                while (!graphLock.isUpdated()) {
                    try {
                        graphLock.wait();
                    } catch (InterruptedException e) {
                    }
                    updateEdges();
                }
            }
        }

        try {
            wait();
        } catch(InterruptedException e) {}

    }*/

}

