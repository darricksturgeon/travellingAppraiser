package travellingappraiser;

/**
 * Created by darrick on 5/9/16.
 */
public class GPS {
    private double latitude;
    private double longitude;


    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPS(double[] latlong) {
        this.latitude = latlong[0];
        this.longitude = latlong[1];
    }

    public GPS(String address) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GPS)) return false;

        GPS gps = (GPS) o;

        if (Double.compare(gps.latitude, latitude) != 0) return false;
        return Double.compare(gps.longitude, longitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
