package apiClientInterface;

import travellingappraiser.geneticAlgorithm.GASettings;

/**
 * Created by darrick on 5/23/16.
 *
 * Essentially the main function of the apiclientinterface.  Takes inputs in the
 * form of a list of strings, finds a distancematrix and geocoordinates, and sets
 * them as static fields for the Genetic Algorithm, in the class GASettings.
 */
public class InitializeData {

    public static void main(String[] args) {

        GASettings.setTotalLocations(args.length-1);

        MatrixFactory matFactory = new MatrixFactory(args);
        GeocoordinateFactory geoFactory = new GeocoordinateFactory(args);


        int[][] distanceMatrix = matFactory.createNewMatrix();
        GASettings.setDistanceMatrix(distanceMatrix);


        double[][] geocoordinates = geoFactory.getGeocoordinates();
        GASettings.setGeocoordinates(geocoordinates);



    }

}
