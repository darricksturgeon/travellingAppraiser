package apiClientInterface;

import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by darrick on 5/12/16.
 */
public class APIClientTest {
    public DistanceMatrixAPIClient client;

    @Before
    public void setUp() throws Exception {
        //DistanceMatrixAPIClient.setApiKey();
        client = new DistanceMatrixAPIClient();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getDistanceMatrix() throws Exception {

        String[] origin = new String[]{"Vancouver B.C., Canada", "Seattle, Washington", "Portland, Oregon"};
        String[] destination = new String[]{"Portland, Oregon", "Seattle, Washington", "Portland, Oregon"};
        DistanceMatrixRequest request = new DistanceMatrixRequest(
                origin,
                destination);
        DistanceMatrix matrix = client.getDistanceMatrix(request);
        DistanceMatrixElement element = matrix.rows[0].elements[0];
        System.out.println(element.duration.inSeconds);
        System.out.println(matrix.getClass().toString());

    }



}