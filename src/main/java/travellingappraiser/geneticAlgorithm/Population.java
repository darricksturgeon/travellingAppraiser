package travellingappraiser.geneticAlgorithm;

/**
 * Created by darrick on 5/10/16.
 */
public class Population {

    Tour[] tours;

    public Population(int populationSize, boolean initialize) {

        tours = new Tour[populationSize];

        if(initialize) {
            for (int i = 0; i < size(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveIndividual(i, newTour);

            }
        }

    }

    public Tour getIndividual(int index) {
        return tours[index];
    }

    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through tours to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    // Get population size
    public int size() {
        return tours.length;
    }

    // Save individual
    public void saveIndividual(int index, Tour indiv) {
        tours[index] = indiv;
    }
}
