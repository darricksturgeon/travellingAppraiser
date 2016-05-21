package apiClientInterface;

import com.google.maps.model.GeocodingResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by darrick on 5/21/16.
 */
public class GeocodingAPIClientTest {

    private GeocodingAPIClient apiClient = new GeocodingAPIClient();

    @Test
    public void getGeocodingResult() throws Exception {

        GeocodingResult[] result = apiClient.getGeocodingResult("221B Baker St, Marylebone, London NW1 6XE, UK");

        assertNotNull(result);
        assertEquals(result[0].geometry.location.lat, 51.5237715,.0000001);
        assertEquals(result[0].geometry.location.lng, -0.1585385,.0000001);


    }

}