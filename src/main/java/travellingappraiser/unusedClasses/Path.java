package travellingappraiser.unusedClasses;

import java.util.Arrays;

/**
 * Created by darrick on 5/9/16.
 */
public class Path implements Comparable {
    public int[] nodes = new int[5];
    public int distance;

    public Path(int[] nodes, int distance) {
        this.nodes = nodes;
        this.distance = distance;
    }

    public boolean conflicts(Path p) {

        for (int i:this.nodes) {
            for (int j:p.nodes) {

                if (i == j) {return true;}

            }
        }
        return false;
    }

    public int compareTo(Object o) {
        return compareTo((Path) o);
    }

    public int compareTo(Path p) {
        return this.distance - p.distance;
    }

    @Override
    public String toString() {
        return "Path{" +
                "nodes=" + Arrays.toString(nodes) +
                ", distance=" + distance +
                '}';
    }
}
