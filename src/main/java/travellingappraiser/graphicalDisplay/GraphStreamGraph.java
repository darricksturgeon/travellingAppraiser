package travellingappraiser.graphicalDisplay;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;

/**
 * Created by darrick on 5/15/16.
 */
public class GraphStreamGraph {

    private static Graph graph;
    private static Viewer viewer;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                startGraph();
                TANodes.addNodes(graph);
                viewer = graph.display(false);

            }
        });

    }

    public static void startGraph() {
        graph = new SingleGraph("Location Map");
    }






}

