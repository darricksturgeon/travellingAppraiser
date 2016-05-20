package travellingappraiser.geneticAlgorithm;

/**
 * Created by darrick on 5/11/16.
 */
public abstract class Defaults {

    public static final int TOTAL_LOCATIONS = 1000;
    public static final int PATH_LENGTH = 1000;
    public static final int ROUTES = (int) Math.ceil((double)TOTAL_LOCATIONS / PATH_LENGTH);
    public static final boolean VARY_ROUTE_LENGTH = true;

}
