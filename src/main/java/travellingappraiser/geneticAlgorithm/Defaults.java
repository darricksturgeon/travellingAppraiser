package travellingappraiser.geneticAlgorithm;

/**
 * Created by darrick on 5/11/16.
 */
public abstract class Defaults {

    public static int TOTAL_LOCATIONS = 200;
    public static int PATH_LENGTH = 200;
    public static int ROUTES = (int) Math.ceil((double)TOTAL_LOCATIONS / PATH_LENGTH);
    public static boolean VARY_ROUTE_LENGTH = true;
    public static String API_KEY = "";

}