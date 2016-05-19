package apiClientInterface;

import java.util.ArrayList;

import static travellingappraiser.geneticAlgorithm.Defaults.TOTAL_LOCATIONS;

/**
 * Created by darrick on 5/19/16.
 */
public class AddressList {

    private ArrayList<AddressElement> addrList =
            new ArrayList<>(TOTAL_LOCATIONS);

    public void addAddress(AddressElement address) {

        addrList.add(address.getId(), address);

    }

    public AddressElement getAddress(int id) {

        if (addrList.get(id) == null) {
            System.out.println("No address found at id: " + id);
        }

        return addrList.get(id);

    }

}
