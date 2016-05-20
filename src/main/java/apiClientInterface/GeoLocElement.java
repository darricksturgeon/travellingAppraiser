package apiClientInterface;

/**
 * Created by darrick on 5/19/16.
 */
public class GeoLocElement extends LocationElement {

    private String latitude;
    private String longitude;

    public GeoLocElement(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*getters and setters*/
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }


}
