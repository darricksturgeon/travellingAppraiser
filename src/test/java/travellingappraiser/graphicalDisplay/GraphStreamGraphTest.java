package travellingappraiser.graphicalDisplay;

import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.junit.Before;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.*;

import javax.swing.*;

import static travellingappraiser.geneticAlgorithm.Defaults.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/15/16.
 */
public class GraphStreamGraphTest {


    public Tour bestTour;
    private GraphStreamGraph graph;
    private double[][] locations;
    private GraphLock graphLock = new GraphLock();


    @Test
    public void runGraphStream() {

        locations = TestObjects.randMatrix(TOTAL_LOCATIONS+1);
        int[][] distanceMatrix = TestObjects.randDistanceMatrix(locations);


        /*this tests the concept of adding 'house viewing' times,
         *which would mean that the final home stretch is eliminated.

        for (int i = 0; i < distanceMatrix[0].length; i++) {
            for (int j = 1; j < distanceMatrix[0].length; j++) {
                distanceMatrix[i][j] += 400;
            }
        }
        */


        Population pop = new Population (20, true);
        FitnessCalc.setDistanceMatrix(distanceMatrix);
        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + -pop.getFittest().getFitness());

        graph = new GraphStreamGraph(locations, bestTour,graphLock);

        graph.startGraph();
        graph.updateEdges();
        Viewer viewer = graph.graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 200; j++) {
                pop = Algorithm.evolvePopulation(pop);
            }
            bestTour = pop.getFittest();
            graph.setTour(bestTour);
            graph.updateEdges();
        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + -pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());




    }

}