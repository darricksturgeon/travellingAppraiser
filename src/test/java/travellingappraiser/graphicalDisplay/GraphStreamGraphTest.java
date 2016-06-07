package travellingappraiser.graphicalDisplay;

import org.graphstream.ui.view.Viewer;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.*;

import static travellingappraiser.geneticAlgorithm.GASettings.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/15/16.
 *
 * Test of the graphing portion of the Graphical output.  Uses Math.random to generate
 * location data.
 */
public class GraphStreamGraphTest {


    private Tour bestTour;
    private GraphStreamGraph graph;
    private double[][] locations;


    @Test
    public void runGraphStream() {
        GASettings.setPathLength(50);
        GASettings.setTotalLocations(50);

        locations = TestObjects.randMatrix(TOTAL_LOCATIONS+1);
        int[][] distanceMatrix = TestObjects.randDistanceMatrix(locations);



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

        for (int i = 0; i < 20000; i++) {
            for (int j = 0; j < 10; j++) {
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