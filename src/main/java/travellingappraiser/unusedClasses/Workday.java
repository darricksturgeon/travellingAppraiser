package travellingappraiser.unusedClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by darrick on 5/9/16.
 */
public class Workday {
    //object which roughly represents an eight hour workday, can be filled as needed.

    private final int totalLength = 8*60;
    private ArrayList<Integer> events = new ArrayList<Integer>();

    //Constructor
    public Workday() {}


    /*adds an 'event' to the workday: a number of minutes that a drive or
    meeting might take.  Boolean is if event can be added successfully.
     */
    public boolean addEvent(int event) {

        if(notFull(event)) {
            events.add(event);
            return true;
        }

        else {return false;}


    }


    /*simple iteration calls addEvent for every element in an array if
    workday has space, else returns false and adds nothing.
     */
    public boolean addManyEvents(int[] events) {

        ArrayList<Integer> storeEvents = this.events;
        boolean isPossible = true;

        for(int i: events) { isPossible = isPossible && addEvent(i); };

        if(isPossible) { return isPossible;}

        else {
            this.events = storeEvents;
            return isPossible;
        }

    }

    public boolean notFull(int event) {

        return remaining() > event;

    }

    private int remaining() {
        Iterator<Integer> iter = events.iterator();
        int currentLength=0;


        while(iter.hasNext()) { currentLength += iter.next().intValue();}
        return totalLength - currentLength;
    }


}
