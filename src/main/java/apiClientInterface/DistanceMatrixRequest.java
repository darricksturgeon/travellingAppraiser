package apiClientInterface;

import java.util.ArrayList;

/**
 * Created by darrick on 5/12/16.
 *
 * Object for the purpose of querying Google Maps for travel durations.
 * Google Distance Matrix API limits responses to 100 elements, where
 * an element is any origin, destination pair.  Therefore
 * origins x destinations should not exceed 100.
 *
 * Fields are two arraylists, representing origins and destinations,
 * which can only be added to.
 */
public class DistanceMatrixRequest {

    private ArrayList<String> oriList = new ArrayList<>();
    private ArrayList<String> destList = new ArrayList<>();

    /*constructors*/
    public DistanceMatrixRequest() {}

    public DistanceMatrixRequest(String[] origins, String[] destinations) {
        addOrigins(origins);
        addDestinations(destinations);
    }


    public void addOrigins(String[] o) {
        oriList.ensureCapacity(oriList.size()+o.length);

        for (String s: o) {oriList.add(s);}
    }


    public void addDestinations(String[] d) {
        destList.ensureCapacity(destList.size()+d.length);

        for (String s: d) {destList.add(s);}

    }

    public String[] getOrigins() {
        return oriList.toArray(new String[0]);
    }

    public String[] getDestinations() {
        return destList.toArray(new String[0]);
    }
}
