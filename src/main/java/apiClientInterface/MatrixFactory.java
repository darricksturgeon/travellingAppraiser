package apiClientInterface;

import com.google.maps.model.DistanceMatrix;

/**
 * Created by darrick on 5/20/16.
 *
 * class to do the groundwork: Create a distance matrix suited for this program.
 * Takes the LocationList object created from User Inputs, sends organized requests
 * via the APIClient and construct replies into an NxN matrix of travel times.
 * In graph theory, the term for such objects is a 'distance matrix' hence there can
 * be some confusion as the replies from google are also 'distance' matrices.
 */
public class MatrixFactory {

    private LocationList locations;
    private int size;
    private int[][] fullMatrix;

    private DistanceMatrixAPIClient apiClient = new DistanceMatrixAPIClient();

    public MatrixFactory(LocationList locations) {
        setLocList(locations);
    }

    public void setLocList(LocationList list) {
        locations = list;
        size = list.size();
    }

    public int[][] createNewMatrix() {
        fullMatrix = new int[size][size];
        fillUpperRight();
        fillLowerLeft();

        return fullMatrix;
    }

    private void fillUpperRight() {

        for (int i = 0; i < size; i++) {
            DistanceMatrixRequest request = new DistanceMatrixRequest();

            request.addOrigins(new String[]{locations.getLocation(i).toString()});
            request.addDestinations(locationSublist(i+1,size));

            DistanceMatrix reply = apiClient.getDistanceMatrix(request);

            addRow(i,reply);

        }

    }

    private void fillLowerLeft() {

        for (int i = 0; i < size; i++) {
            DistanceMatrixRequest request = new DistanceMatrixRequest();

            request.addDestinations(new String[]{locations.getLocation(i).toString()});
            request.addOrigins(locationSublist(i+1,size));

            DistanceMatrix reply = apiClient.getDistanceMatrix(request);

            addColumn(i,reply);

        }

    }


    //returns a String[] of the elements from LocList from index to end-1.
    // Typically end = list size.
    private String[] locationSublist(int index, int end) {

        String[] subList = new String[end-index];

        for (int i = 0; i+index < end; i++) {
            subList[i] = locations.getLocation(i+index).toString();
        }

        return subList;

    }

    private void addRow(int row, DistanceMatrix reply) {

        for (int i = row+1; i < reply.rows[0].elements.length; i++) {
            fullMatrix[row][i] = (int) reply.rows[0].elements[i].duration.inSeconds;
        }

    }

    private void addColumn(int column, DistanceMatrix reply) {

        for (int i = column+1; i < reply.rows.length; i++) {
            fullMatrix[i][column] = (int) reply.rows[i].elements[0].duration.inSeconds;
        }

    }

    private void Undirected2Directed() {

        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                fullMatrix[i][j] = (fullMatrix[i][j]+fullMatrix[j][i])/2;
            }
        }

    }

}
