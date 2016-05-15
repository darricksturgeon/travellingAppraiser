package apiClientInterface;

/**
 * Created by darrick on 5/12/16.
 */
public class DistanceMatrixRequest {

    private String[] origins;
    private String[] destinations;

    /*
    private boolean asLatLong;
    */

    public DistanceMatrixRequest() {

    }

    public DistanceMatrixRequest(String[] origins, String[] destinations) {
        this.origins = origins;
        this.destinations = destinations;
    }

    public String[] getOrigins() {
        return origins;
    }

    public void setOrigins(String[] origins) {
        this.origins = origins;
    }

    public String[] getDestinations() {
        return destinations;
    }

    public void setDestinations(String[] destinations) {
        this.destinations = destinations;
    }

    /*
    public boolean isAsLatLong() {
        return asLatLong;
    }

    public void setAsLatLong(boolean asLatLong) {
        this.asLatLong = asLatLong;
    }
    */
}
