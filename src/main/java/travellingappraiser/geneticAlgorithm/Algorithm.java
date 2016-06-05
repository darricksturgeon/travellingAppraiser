package travellingappraiser.geneticAlgorithm;

import static travellingappraiser.geneticAlgorithm.GASettings.*;

/**
 * Created by darrick on 5/10/16.
 *
 * Contains the functions of genetic algorithms: mutation and crossover.
 */
public class Algorithm {

    /* GA parameters */
    private static final double flipMutationRate = 0.12;
    private static final double swapMutationRate = 0.5;
    private static final int tournamentSize = 8;
    private static final boolean elitism = true;

    /* Public methods */

    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // save fittest
        int elitismOffset;
        if (elitism) {
            newPopulation.saveIndividual(0,pop.getFittest());
            elitismOffset = 1;
        }
        // Loop over the population size and create new tours with
        // simpleCrossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Tour parent1 = tournamentSelection(pop);
            Tour parent2;
            do { parent2 = tournamentSelection(pop);
            } while(parent2==parent1);

            Tour child = simpleCrossover(parent1,parent2);
            newPopulation.saveIndividual(i, child);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // simpleCrossover is to merely copy parent1, the dominant parent, and replace a section with parent2
    private static Tour simpleCrossover(Tour parent1, Tour parent2) {
        Tour child = new Tour(parent1);

        //gets from parent2, a random route, then a random subroute of that route
        int routeNumber = (int) (Math.random()* ROUTES);
        short[] route = parent2.getRoute(routeNumber);

        int subrouteLength = (int) (1+(route.length-1)*Math.random());
        int subroutePosition = (int) ((route.length-subrouteLength)*Math.random());
        short[] subroute = new short[subrouteLength];

        System.arraycopy(route, subroutePosition, subroute, 0, subrouteLength);


        //delete the duplicate information in child.
        for (int i = 0; i < child.size(); i++) {
            for (short s:subroute) {
                if(child.getGene(i)==s) {
                    child.setGene(i,(short)0);
                }
            }
        }

        child = child.bestInsertion(subroute);

        return child;
    }

    // Mutate an individual
    private static void mutate(Tour tour) {

        if(Math.random()<swapMutationRate) {
            mutateSwap(tour);
        }

        if(Math.random()<flipMutationRate){
            mutateFlip(tour);
        }

    }

    //swap 2 random genes
    private static void mutateSwap(Tour tour) {

        int index1 = (int) (Math.random()*TOTAL_LOCATIONS-1);
        int index2 = (int) (Math.random()*TOTAL_LOCATIONS-1);

        short gene1 = tour.getGene(index1);
        tour.setGene(index1, tour.getGene(index2));
        tour.setGene(index2, gene1);
    }

    //swap the order of a subroute
    private static void mutateFlip(Tour tour) {

        int index = (int) (Math.random()*(TOTAL_LOCATIONS -4));
        int length = (int) (3+Math.random()*(TOTAL_LOCATIONS-4-index));

        short[] flipValues = new short[length];
        for (int i=0;i<length;i++) {
            flipValues[i] = tour.getGene(index+length-1-i);
        }
        for (int i = 0; i < length; i++) {
            tour.setGene(index+i,flipValues[i]);
        }

    }

    //
    private static void mutateSlide(Tour tour) {



    }

    // Select tours for simpleCrossover
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        return tournament.getFittest();
    }
}