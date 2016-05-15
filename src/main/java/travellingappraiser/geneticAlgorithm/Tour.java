package travellingappraiser.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static travellingappraiser.geneticAlgorithm.Defaults.*;


/**
 * Created by darrick on 5/10/16.
 * Genes represent solutions to the problem in the form of an array of shorts (for memory efficiency)
 * The structure of genes is an array with N enumerated locations followed by M path lengths (number of nodes).
 * an example array: {4, 2, 15, ..., 14 } | {3, 5, 4, 6} is an array of 18 locations appended to 4
 * path lengths.  Note the sum of path lengths is 16, hence the shorts in the first part are 1-16.
 *
 * For the purpose of understanding the meaning of the genes:
 * in the example array above, the path length is 3.  Then the first 3 shorts are 4, 2, 15, and
 * represent the first path.  Home is inherently 0, so we have a graph 0 -> 4 -> 2 -> 15 -> 0.
 */

public class Tour {

    private short[] genes = new short[TOTAL_LOCATIONS + ROUTES];
    // Cache
    private double fitness = 0;

    public Tour() {}

    public Tour(Tour tour) {
        this.genes = tour.genes.clone();
    }

    // Create a random individual
    public void generateIndividual() {

        this.shuffleGenes();

        for (int i = 0; i < ROUTES; i++) {
            setRoute(i,(short)PATH_LENGTH);
        }

    }

    private void shuffleGenes() {
        short[] geneArray = new short[TOTAL_LOCATIONS];
        for (int i = 0; i < TOTAL_LOCATIONS; i++) {
            geneArray[i] = (short) (i + 1);
        }
        //Fisher-Yates algorithm for shuffling a set.  We only shuffle locations, not break data.
        Random rnd = ThreadLocalRandom.current();
        for (int i = geneArray.length-1;i>0;i--) {
            int index = rnd.nextInt(i+1);
            short a = geneArray[index];
            geneArray[index] = geneArray[i];
            geneArray[i] = a;
        }

        for (int i = 0; i < TOTAL_LOCATIONS; i++) {
            this.setGene(i, geneArray[i]);
        }
    }

    //takes a subroute and finds the best possible position for the subroute.
    public Tour bestInsertion(short[] subroute) {

        ArrayList<Tour> possibleInsertions = new ArrayList<>(TOTAL_LOCATIONS + 1);
        /*
        if we have an array size L, split into M parts of length given by getRouteData...
        each Route is length K, then for all M s.t. subroute.length <= k, there are
        (k - subroute.length + 1)*M possible positions for that subroute.  Or -
        we could iterate over all possible orientations, though this will generally be
        more costly as it is L - subroute.length.
        */

        short[] routeData = this.getRouteData();

        //run every legal insertion of the subroute.
        int index = 0;
        for(short s: routeData) {
            for (int j = 0; (s >= subroute.length) && (j < ((s - subroute.length) + 1)); j++) {
                possibleInsertions.add(tryInsertion(index+j,subroute));
            }
            index+=s;
        }

        Tour bestTour = possibleInsertions.get(0);
        double bestFitness = possibleInsertions.get(0).getFitness();
        for (Tour t : possibleInsertions) {
            if(bestFitness<t.getFitness()) {
                bestFitness = t.getFitness();
                bestTour = t;
            }
        }

        //this = bestTour;
        return bestTour;

    }

    private Tour tryInsertion(int index, short[] subroute) {
        Tour ghostTour = new Tour(this);
        short[] replacedGenes = new short[subroute.length];

        for (int i = 0; i <subroute.length ; i++) {
            replacedGenes[i] = this.getGene(index+i);
            ghostTour.setGene(index+i, subroute[i]);
        }

        for (short s:replacedGenes) {
            if(s!=0) {
                ghostTour
                        .setGene(ghostTour.findGeneIndex((short) 0),s);
            }
        }
        return ghostTour;
    }


    /* Getters and setters */

    public short getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, short value) {
        genes[index] = value;
        fitness = 0;
    }

    //searches for a particular gene, returns -1 if no such gene exists.
    public int findGeneIndex(short value) {

        for (int index = 0; index<this.size() ; index++) {
            if(this.getGene(index)==value) {return index;}
        }
        return -1;
    }

    //returns the end of genes where route lengths are stored
    public short[] getRouteData() {
        return Arrays.copyOfRange(
                this.genes,
                TOTAL_LOCATIONS,
                TOTAL_LOCATIONS+ ROUTES);
    }

    //returns specified route
    public short[] getRoute(int route) {

        int[] indeces = getRouteIndeces(route);
        return Arrays.copyOfRange(
                this.genes,
                indeces[0],
                indeces[1]+1);

    }
    //returns {point where route begins, length of route}.  Input is route# starting with 0.
    public int[] getRouteIndeces(int route) {
        int startpoint = 0;
        int endpoint;
        if (!VARY_ROUTE_LENGTH) {
            startpoint = PATH_LENGTH*route;
            endpoint = startpoint+PATH_LENGTH;
        } else {
            for (int i = 0; i < route ; i++) {
                startpoint+= this.getRouteLength(i);
            }
            endpoint = startpoint + this.getRouteLength(route);
        }
        return new int[]{startpoint,endpoint};
    }

    public short getRouteLength(int i) {
        return this.genes[TOTAL_LOCATIONS+i];
    }

    /* Public methods */
    public int size() {
        return genes.length - ROUTES;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    public void setRoute(int index, short routelength) {
        this.genes[TOTAL_LOCATIONS+index] = routelength;
    }


    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i<size(); i++) {
            geneString += getGene(i) + " ";
        }
        geneString+= "Routes: ";

        for (int i = 0; i < ROUTES; i++) {
            geneString+= getRouteLength(i);
        }
        return geneString;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        return Arrays.equals(genes, tour.genes);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Arrays.hashCode(genes);
        temp = Double.doubleToLongBits(fitness);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}