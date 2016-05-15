import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;
import org.junit.Test;
import travellingappraiser.geneticAlgorithm.Algorithm;
import travellingappraiser.geneticAlgorithm.FitnessCalc;
import travellingappraiser.geneticAlgorithm.Population;

import java.util.Arrays;

/**
 * Created by darrick on 5/14/16.
 */
public class GATest {


    @Test
    public void runGeneticAlgorithmTest() {

        int[][] distanceMatrix = TestObjects.randDistanceMatrix(36);
        FitnessCalc.setDistanceMatrix(distanceMatrix);

        FitnessCalc.getDistanceMatrix();

        Population pop = new Population (50, true);
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + 1/pop.getFittest().getFitness());


        for (int i = 0; i < 1000; i++) {
            pop = Algorithm.evolvePopulation(pop);
        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + 1/pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());


    }



}
