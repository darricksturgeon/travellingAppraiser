package travellingappraiser.graphicalDisplay;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import travellingappraiser.geneticAlgorithm.Tour;

/**
 * Created by darrick on 5/15/16.
 *
 * Initializes, maintains, and displays a graph using a field "tour" shared by GARun.
 */
public class GraphStreamGraph implements Runnable {

    //fields for Graphical Display
    private Graph graph;
    private Viewer viewer;
    private GraphLock graphLock;


    //fields for Graph G(V,E) computation
    private final float[][] nodes;  //Home is at row 0.  Rest must be consistent with intial user input order.
    private int[] edges;
    private final Tour tour;  //points to a tour, "bestTour" in RunGA
        private short[] genes;
        private short[] routes;

    public GraphStreamGraph(double[][] locations, Tour tour, GraphLock graphLock) {
        this.graphLock = graphLock;
        this.tour = tour;

        nodes = new float[locations.length][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < locations.length; j++) {
                nodes[j][i] = (float) locations[j][i];
            }
        }
    }



    public void run() {

        startGraph();
        addNodes();
        viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);
        viewer.disableAutoLayout();


        //have to try synchronization with Graphstreamgraphtest
        synchronized (graphLock) {
            while (!graphLock.isFinished()) {
                while (!graphLock.isUpdated()) {
                    try {
                        graphLock.wait();
                    } catch (InterruptedException e) {}
                }
                setEdges();
                updateEdges();
                viewer.newViewerPipe();
            }
        }

        try {
            wait();
        } catch(InterruptedException e) {}

    }

    private void startGraph() {
        graph = new SingleGraph("Location Map");
    }

    //takes a set of genes and constructs an array which describes each circuit.
    private void setEdges() {
        updateGenes();

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

    private void addNodes() {
        for (int i = 0; i < nodes.length; i++) {
            Node n = graph.addNode("" + i);
            n.addAttribute("xy", nodes[i][0],nodes[i][1]);  //breakpoint is to remember that we're not sure if this works.
        }
    }

    private void updateEdges() {
        while(graph.getEdgeCount()>0) {
            graph.removeEdge(0);
        }

        for (int i = 0; i < edges.length-1; i++) {
            graph.addEdge(""+i,edges[i],edges[i+1]);
        }
        graphLock.setUpdated(false);
    }

    private void updateGenes() {
        this.routes = this.tour.getRouteData();
        this.genes = this.tour.getPathing();


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

}

