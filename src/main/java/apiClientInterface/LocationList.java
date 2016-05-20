package apiClientInterface;

import java.util.ArrayList;

import static travellingappraiser.geneticAlgorithm.Defaults.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/19/16.
 *
 * Array List of LocationElements that will attempt to downcast any
 * object to the proper type.
 */
public class LocationList {

    private ArrayList<LocationElement> locList =
            new ArrayList<>(TOTAL_LOCATIONS+1);

    public void addLocation(Object object) {

        try {
            LocationElement location = (LocationElement) object;
            locList.add(location.getId(), location);
        } catch (ClassCastException c) {
            System.out.println("Could not add location.  Make sure class extends LocationElement.");}

    }

    public void updateLocation(Object object) {
        try {
            LocationElement location = (LocationElement) object;
            locList.remove(location.getId());
            locList.add(location.getId(), location);
        } catch (ClassCastException c) {
            System.out.println("Could not add location.  Make sure class extends LocationElement.");}
    }

    public LocationElement getLocation(int id) {

        if (locList.get(id) == null) {
            System.out.println("No address found at id: " + id);
        }

        return locList.get(id);

    }
}
