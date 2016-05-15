package apiClientInterface;

import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by darrick on 5/12/16.
 */
public class APIClientTest {
    public APIClient client;

    @Before
    public void setUp() throws Exception {
        APIClient.setApiKey("");
        client = new APIClient();




    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getDistanceMatrix() throws Exception {

        String[] origin = new String[]{"Vancouver B.C., Canada"};
        String[] destination = new String[]{"Portland, Oregon"};
        DistanceMatrixRequest request = new DistanceMatrixRequest(
                origin,
                destination);
        DistanceMatrix matrix = client.getDistanceMatrix(request);

        System.out.println(matrix.getClass().toString());

    }



}