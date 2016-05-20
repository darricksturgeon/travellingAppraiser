package apiClientInterface;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by darrick on 5/19/16.
 */



public class AddressElement extends LocationElement {

    private String street = "";
    private String city = "";
    private String state = "";
    private String zip = "";


    public AddressElement() {}

    public AddressElement(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /*getters and setters*/
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return getStreet() + " "
                + getCity() + ", "
                + getState() + " "
                +getZip();
    }
}



