package travellingappraiser.geneticAlgorithm;

/**
 * Created by darrick on 5/11/16.
 */
public class GAParameters {

    public GAParameters() {}

    public static String API_KEY = "";

    public static int TOTAL_LOCATIONS;
    static int PATH_LENGTH = 5;
    static int ROUTES;
    static int populationSize = 20;
    static int generations = 200000;

    private static boolean matrixUndirected;
    private static boolean noOriginWeight;
    private static boolean customRoute=false;
    private static int[] customRouteArray;
    private static int[][] distanceMatrix;
    static double[][] geocoordinates;

    public static double[][] getGeocoordinates() {
        return geocoordinates;
    }

    public static void setGeocoordinates(double[][] geocoordinates) {
        GAParameters.geocoordinates = geocoordinates;
    }

    public static boolean isCustomRoute() {
        return customRoute;
    }

    public static void setCustomRoute(boolean customRoute) {
        GAParameters.customRoute = customRoute;
    }

    public static int[] getCustomRouteArray() {
        return customRouteArray;
    }

    public static void setCustomRouteArray(int[] customRouteArray) {
        GAParameters.customRouteArray = customRouteArray;
    }

    public static int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public static void setDistanceMatrix(int[][] distanceMatrix) {
        GAParameters.distanceMatrix = distanceMatrix;
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
        ROUTES = (int) Math.ceil((double) TOTAL_LOCATIONS / PATH_LENGTH);
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
        GAParameters.ROUTES = ROUTES;
    }

    public static boolean isMatrixUndirected() {
        return matrixUndirected;
    }

    public static void setMatrixUndirected(boolean matrixUndirected) {
        GAParameters.matrixUndirected = matrixUndirected;
    }

    public static boolean isNoOriginWeight() {
        return noOriginWeight;
    }

    public static void setNoOriginWeight(boolean noOriginWeight) {
        GAParameters.noOriginWeight = noOriginWeight;
    }

    //function to create file which stores defaults? ...



}