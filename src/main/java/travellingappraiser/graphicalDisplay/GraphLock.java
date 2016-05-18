package travellingappraiser.graphicalDisplay;

/**
 * Created by darrick on 5/18/16.
 */
public class GraphLock {
    private boolean updated;
    private boolean finished;

    public GraphLock() {
        this.updated = false;
        this.finished = false;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
