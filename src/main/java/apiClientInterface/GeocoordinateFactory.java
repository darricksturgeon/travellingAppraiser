package apiClientInterface;

import com.google.maps.model.LatLng;

/**
 * Created by darrick on 5/21/16.
 */
public class GeocoordinateFactory {

    private String[] locations;
    private GeocodingAPIClient  apiClient = new GeocodingAPIClient();

    public GeocoordinateFactory(String[] locations) {
        this.locations = locations;
    }

    public double[][] getGeocoordinates() {

        double[][] geoCoordinates = new double[locations.length][2];

        for (int i = 0; i < locations.length; i++) {
            LatLng latLng = apiClient
                    .getGeocodingResult(locations[i]
                            .toString())[0]
                    .geometry.location;

            geoCoordinates[i][0] = latLng.lat;
            geoCoordinates[i][0] = latLng.lng;
        }

        return geoCoordinates;

    }

    public double[] getGeooordinate(String address) {
        LatLng latLng = apiClient
                .getGeocodingResult(address)[0]
                .geometry
                .location;

        return new double[]{latLng.lat,latLng.lng};
    }

}
