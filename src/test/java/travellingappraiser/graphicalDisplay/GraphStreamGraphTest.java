package travellingappraiser.graphicalDisplay;

import org.junit.Before;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.*;

/**
 * Created by darrick on 5/15/16.
 */
public class GraphStreamGraphTest {


    private Tour bestTour;
    private GraphLock graphLock;
    private double[][] locations;



    @Test
    public void runGraphStream() {

        graphLock = new GraphLock();
        locations = TestObjects.randMatrix(36);
        int[][] distanceMatrix = TestObjects.randDistanceMatrix(locations);

        Population pop = new Population (20, true);
        FitnessCalc.setDistanceMatrix(distanceMatrix);
        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + 1/pop.getFittest().getFitness());


        Thread graphThread = new Thread(
                new GraphStreamGraph(locations, bestTour,graphLock));

        graphThread.start();

        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 1000; i++) {
                pop = Algorithm.evolvePopulation(pop);
            }
            bestTour = pop.getFittest();

            synchronized (graphLock) {
                graphLock.setUpdated(true);
                graphLock.notifyAll();
            }

        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + 1/pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());



    }

}