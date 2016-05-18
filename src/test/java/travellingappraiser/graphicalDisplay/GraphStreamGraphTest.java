package travellingappraiser.graphicalDisplay;

import org.junit.Before;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.Population;
import travellingappraiser.geneticAlgorithm.Tour;

/**
 * Created by darrick on 5/15/16.
 */
public class GraphStreamGraphTest {

    private double[][] locations;
    private Tour bestTour;


    @Before
    public void addObjects() {

        Population pop = new Population (20, true);
        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + 1/pop.getFittest().getFitness());

    }

    @Test
    public void runGraphStream() {
        Runnable testGraph = new GraphStreamGraph(locations, bestTour);

        testGraph.run();


    }

}