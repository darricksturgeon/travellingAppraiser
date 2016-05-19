package apiClientInterface;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;

import java.util.concurrent.TimeUnit;

/**
 * Created by darrick on 5/12/16.
 */
public class DistanceMatrixAPIClient {

    private GeoApiContext context = new GeoApiContext();

    private static String apiKey;

    public DistanceMatrixAPIClient() {
        this.context = context
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS)
                .setApiKey(apiKey);

    }

    public DistanceMatrix getDistanceMatrix(DistanceMatrixRequest request) {

        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                    .origins(request.getOrigins())
                    .destinations(request.getDestinations())
                    .await();
            return matrix;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting Distance Matrix");
            return null;
        }



    }

    // Getters and Setters
    public static void setApiKey(String apiKey) {
        DistanceMatrixAPIClient.apiKey = apiKey;
    }
}
