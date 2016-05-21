package apiClientInterface;

/**
 * Created by darrick on 5/21/16.
 */
public class GeocoorinateFactory {

    private final LocationList locations;
    private GeocodingAPIClient  apiClient = new GeocodingAPIClient();

    public GeocoorinateFactory(LocationList locations) {
        this.locations = locations;
    }
    /*
    public double[][] getGeocoordinates() {
        geoCoordinates = new double[][]


    }
    */
}
