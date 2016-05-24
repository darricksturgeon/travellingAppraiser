package apiClientInterface;

import travellingappraiser.geneticAlgorithm.Parameters;

/**
 * Created by darrick on 5/23/16.
 */
public class InitializeData {

    public static void main(String[] args) {

        Parameters.setTotalLocations(args.length-1);

        MatrixFactory matFactory = new MatrixFactory(args);
        GeocoordinateFactory geoFactory = new GeocoordinateFactory(args);


        int[][] distanceMatrix = matFactory.createNewMatrix();
        Parameters.setDistanceMatrix(distanceMatrix);


        double[][] geocoordinates = geoFactory.getGeocoordinates();
        Parameters.setGeocoordinates(geocoordinates);



    }

}
