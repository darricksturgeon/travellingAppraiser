package travellingappraiser.geneticAlgorithm;

import org.junit.Test;

import static travellingappraiser.geneticAlgorithm.GASettings.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/14/16.
 *
 * Test to see if the classes of geneticAlgorithm package can be fit together properly.
 * Essentially GARunTest.
 */
public class GATest {


    @Test
    public void runGeneticAlgorithmTest() {
        double[][] locations = TestObjects.randMatrix(TOTAL_LOCATIONS+1);
        int[][] distanceMatrix = TestObjects.randDistanceMatrix(locations);
        FitnessCalc.setDistanceMatrix(distanceMatrix);

        Population pop = new Population (20, true);
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + 1/pop.getFittest().getFitness());


        for (int i = 0; i < 100000; i++) {
            pop = Algorithm.evolvePopulation(pop);
        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + 1/pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());


    }



}
