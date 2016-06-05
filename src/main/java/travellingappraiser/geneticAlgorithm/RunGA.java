package travellingappraiser.geneticAlgorithm;

import apiClientInterface.MatrixFactory;
import javafx.collections.ObservableList;
import org.graphstream.ui.view.Viewer;
import travellingappraiser.graphicalDisplay.GraphStreamGraph;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static travellingappraiser.geneticAlgorithm.GASettings.*;

/**
 * Created by darrick on 5/14/16.
 *
 * RunGA is the main method of the geneticAlgorithm package.  Together with settings from
 * GASettings it will guild the process of computation and outputs the final results
 * as urls, and attempts to call the user's browser to display them.
 */
public class RunGA implements Runnable {

    private Tour bestTour;
    private GraphStreamGraph graph;
    private ObservableList<String> items;


    public void run() {

        Population pop = new Population(populationSize, true);

        int[][] paramMatrix = getDistanceMatrix().clone();
        if (isMatrixUndirected()) {
            paramMatrix = MatrixFactory.Directed2Undirected(paramMatrix);
        }
        if(isNoOriginWeight()) {
            paramMatrix = MatrixFactory.removeOriginWeight(paramMatrix);
        }
        FitnessCalc.setDistanceMatrix(paramMatrix);

        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + -pop.getFittest().getFitness());

        graph = new GraphStreamGraph(geocoordinates, bestTour);

        graph.startGraph();
        graph.updateEdges();
        Viewer viewer = graph.graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

        for (int i = 0; i < generations/100; i++) {
            for (int j = 0; j < 100; j++) {
                pop = Algorithm.evolvePopulation(pop);
            }
            bestTour = pop.getFittest();
            graph.setTour(bestTour);
            graph.updateEdges();
        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + -pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());

        for (int i = 0; i < getROUTES(); i++) {
            try {
                googleWaypoints(bestTour.getRoute(i));
            } catch (Exception e) {e.printStackTrace();}
        }


    }

    public void setItems(ObservableList<String> items) { this.items = items;}

    //construct a url for google maps to display a circuit
    public void googleWaypoints(short[] locs) throws URISyntaxException, IOException {

        String uriString = "http://maps.google.com/maps?";

        uriString += "saddr=" + items.get(0).replace(' ','+') + "&";
        uriString += "daddr=" + items.get(locs[0]).replace(' ','+');

        for (int i = 1; i < locs.length; i++) {
            uriString += "+to:" + items.get(locs[i]).replace(' ', '+');
        }
        uriString += "+to:" + items.get(0).replace(' ','+');

        URI uri = new URI(uriString);
        if(Desktop.isDesktopSupported()) {Desktop.getDesktop().browse(uri);}
        System.out.println(uriString);

    }

}
