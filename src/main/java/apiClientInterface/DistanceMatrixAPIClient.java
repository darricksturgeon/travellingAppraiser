package apiClientInterface;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;

import java.util.concurrent.TimeUnit;

import static travellingappraiser.geneticAlgorithm.GASettings.API_KEY;

/**
 * Created by darrick on 5/12/16.
 *
 * Communicates with google Distance Matrix API.  Constructor sets up the connection and
 * getDistanceMatrix(request) takes a DistanceMatrixRequest and returns a DistanceMatrix
 * java object as defined in Google's API.
 */
public class DistanceMatrixAPIClient {

    private GeoApiContext context = new GeoApiContext();

    public DistanceMatrixAPIClient() {
        this.context = context
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS)
                .setApiKey(API_KEY);

    }

    public DistanceMatrix getDistanceMatrix(DistanceMatrixRequest request) {

        try {
            return DistanceMatrixApi.newRequest(context)
                    .origins(request.getOrigins())
                    .destinations(request.getDestinations())
                    .await();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getting Distance Matrix");
            return null;
        }



    }

}
