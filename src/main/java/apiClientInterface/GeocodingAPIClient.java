package apiClientInterface;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import java.util.concurrent.TimeUnit;

import static travellingappraiser.geneticAlgorithm.Defaults.API_KEY;

/**
 * Created by darrick on 5/20/16.
 */
public class GeocodingAPIClient {

    private GeoApiContext context = new GeoApiContext();

    public GeocodingAPIClient() {
        this.context = context
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS)
                .setApiKey(API_KEY);
    }

    public GeocodingResult[] getGeocodingResult(String address) {

        try {
            GeocodingResult[] result = GeocodingApi.newRequest(context)
                    .address(address).await();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting geocoordinates for: " + address);
            return null;
        }

    }
}
