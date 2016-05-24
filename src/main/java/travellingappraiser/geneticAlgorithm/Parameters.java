package travellingappraiser.geneticAlgorithm;

/**
 * Created by darrick on 5/11/16.
 */
public class Parameters {

    public Parameters() {}

    public static String API_KEY = "AIzaSyAYfhjI4BX7vUDqtdrzNPjkRtUYH8ptC5o";

    public static int TOTAL_LOCATIONS =1000;
    static int PATH_LENGTH=1000;
    static int ROUTES = (int) Math.ceil((double) TOTAL_LOCATIONS / PATH_LENGTH);
    static int populationSize = 20;
    static int generations = 200000;

    static boolean matrixIsDirected;
    static boolean noOriginWeight;
    static boolean customRoute;
    static int[] customRouteArray;
    static int[][] distanceMatrix;
    static double[][] geocoordinates;

    public static double[][] getGeocoordinates() {
        return geocoordinates;
    }

    public static void setGeocoordinates(double[][] geocoordinates) {
        Parameters.geocoordinates = geocoordinates;
    }

    public static boolean isCustomRoute() {
        return customRoute;
    }

    public static void setCustomRoute(boolean customRoute) {
        Parameters.customRoute = customRoute;
    }

    public static int[] getCustomRouteArray() {
        return customRouteArray;
    }

    public static void setCustomRouteArray(int[] customRouteArray) {
        Parameters.customRouteArray = customRouteArray;
    }

    public static int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public static void setDistanceMatrix(int[][] distanceMatrix) {
        Parameters.distanceMatrix = distanceMatrix;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static int getTotalLocations() {
        return TOTAL_LOCATIONS;
    }

    public static void setTotalLocations(int totalLocations) {
        TOTAL_LOCATIONS = totalLocations;
    }

    public static int getPathLength() {
        return PATH_LENGTH;
    }

    public static void setPathLength(int pathLength) {
        PATH_LENGTH = pathLength;
    }

    public static int getROUTES() {
        return ROUTES;
    }

    public static void setROUTES(int ROUTES) {
        Parameters.ROUTES = ROUTES;
    }

    public static boolean isMatrixIsDirected() {
        return matrixIsDirected;
    }

    public static void setMatrixIsDirected(boolean matrixIsDirected) {
        Parameters.matrixIsDirected = matrixIsDirected;
    }

    public static boolean isNoOriginWeight() {
        return noOriginWeight;
    }

    public static void setNoOriginWeight(boolean noOriginWeight) {
        Parameters.noOriginWeight = noOriginWeight;
    }

    //function to create file which stores defaults? ...



}