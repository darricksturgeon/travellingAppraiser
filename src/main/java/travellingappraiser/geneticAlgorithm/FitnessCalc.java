package travellingappraiser.geneticAlgorithm;

import static travellingappraiser.geneticAlgorithm.GASettings.ROUTES;

/**
 * Created by darrick on 5/10/16.
 *
 * Calculates the fitness of a Tour.  At the moment, fitness is simply -(total distance)
 * where total distance is the sum over all routes of the Tour.  It is negative so that
 * any suitable definition of fitness need only satisfy:
 * Better fitness score > Worse fitness score
 */
public class FitnessCalc {

    /*Here is where the fun begins.  The strings represent nodes, wherein the weights of edges
    are given by a distance matrix C, with zeros on the diagonal and travel time from locations
    i -> j is given by the element i,j.
     */

    private static int[][] distanceMatrix;


    //The fitness scores should incorporate a reasonable work day.
    private static double maxHours = 8.0;




    public static double getFitness(Tour tour) {

        int totalDistance = 0;
        int currentIndex = 0;
        for(int i = 0; i < ROUTES; i++) {
            int routeNodes = (int) tour.getRouteLength(i);

            int routeDistance = findRouteDistance(tour, routeNodes,currentIndex);

            currentIndex+= routeNodes;
            totalDistance+= routeDistance;
        }


        return -totalDistance; //not good enough.  Need to brainstorm a better solution.
    }

    private static int findRouteDistance(Tour tour, int nodes, int index) {

        int routeDistance = 0;

        //adds elements of the distance matrix, where 0 represents home base, loops through nodes then returns.
        routeDistance += getMatrixElement(0,tour.getGene(index));

        for (int i = index; i<index+nodes-1; i++) {
            routeDistance += getMatrixElement(
                    tour.getGene(i),tour.getGene(i+1));
        }

        routeDistance += getMatrixElement(tour.getGene(index+nodes-1), 0);

        return routeDistance;
    }

    private static int getMatrixElement(int i, int j) {
        return distanceMatrix[i][j];
    }

    public static void setDistanceMatrix(int[][] matrix) {
        distanceMatrix = matrix;
    }


    public static int[][] getDistanceMatrix() {
        return distanceMatrix;
    }
}
