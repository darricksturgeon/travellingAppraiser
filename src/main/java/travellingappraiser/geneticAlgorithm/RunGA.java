package travellingappraiser.geneticAlgorithm;

import travellingappraiser.graphicalDisplay.GraphStreamGraph;

/**
 * Created by darrick on 5/14/16.
 */
public class RunGA {

    Tour bestTour;
    boolean GAupdated;
    boolean GAcomplete;


    public static void main(String[] args) {

        Population pop = new Population (20, true);

        new Thread(
                new GraphStreamGraph(locations, bestTour, GAupdated,GAcomplete));




    }



}
