package apiClientInterface;

import travellingappraiser.geneticAlgorithm.GAParameters;

/**
 * Created by darrick on 5/23/16.
 */
public class InitializeData {

    public static void main(String[] args) {

        GAParameters.setTotalLocations(args.length-1);

        MatrixFactory matFactory = new MatrixFactory(args);
        GeocoordinateFactory geoFactory = new GeocoordinateFactory(args);


        int[][] distanceMatrix = matFactory.createNewMatrix();
        GAParameters.setDistanceMatrix(distanceMatrix);


        double[][] geocoordinates = geoFactory.getGeocoordinates();
        GAParameters.setGeocoordinates(geocoordinates);



    }

}
