package apiClientInterface;

import com.google.maps.model.LatLng;

import static java.lang.Math.cos;

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
                    .getGeocodingResult(locations[i])[0]
                    .geometry.location;

            //for small scale, (lat,lng) -> (y,x).  Here we convert to kms as well.
            geoCoordinates[i][1] = latLng.lat*110.574;
            geoCoordinates[i][0] = latLng.lng*111.320*cos(latLng.lat * 3.14159/180);
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
