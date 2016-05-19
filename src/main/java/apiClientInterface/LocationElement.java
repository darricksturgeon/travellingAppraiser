package apiClientInterface;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by darrick on 5/19/16.
 */
public abstract class LocationElement {

    private static final AtomicInteger idCount = new AtomicInteger(0);
    private final int id;

    public LocationElement() {
        this.id = idCount.incrementAndGet();
    }

    public int getId() {
       return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationElement that = (LocationElement) o;

        return getId() == that.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
