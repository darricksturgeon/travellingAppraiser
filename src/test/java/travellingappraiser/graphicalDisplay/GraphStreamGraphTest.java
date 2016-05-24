package travellingappraiser.graphicalDisplay;

import org.graphstream.ui.view.Viewer;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.*;

import static travellingappraiser.geneticAlgorithm.GAParameters.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/15/16.
 */
public class GraphStreamGraphTest {


    private Tour bestTour;
    private GraphStreamGraph graph;
    private double[][] locations;


    @Test
    public void runGraphStream() {

        locations = TestObjects.randMatrix(TOTAL_LOCATIONS+1);
        int[][] distanceMatrix = TestObjects.randDistanceMatrix(locations);


        /*this tests the concept of adding 'house viewing' times
        * by changing the distance of the last leg, making it less important*/

        /*
        for (int i = 0; i < distanceMatrix[0].length; i++) {
                distanceMatrix[i][0] -= 3000;
        }
        */

        /*this tests the concept of 'closest houses', by weighting travel to/from home
        as 0. */

        /*
        for (int i = 0; i < distanceMatrix[0].length; i++) {
            distanceMatrix[i][0] = 0;
            distanceMatrix[0][i] = 0;
        }
        */





        Population pop = new Population (20, true);
        FitnessCalc.setDistanceMatrix(distanceMatrix);
        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + -pop.getFittest().getFitness());

        graph = new GraphStreamGraph(locations, bestTour);

        graph.startGraph();
        graph.updateEdges();
        Viewer viewer = graph.graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);
        viewer.enableXYZfeedback(true);

        for (int i = 0; i < 2000000; i++) {
            for (int j = 0; j < 1; j++) {
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