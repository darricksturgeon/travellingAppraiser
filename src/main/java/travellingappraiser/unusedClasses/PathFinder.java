package travellingappraiser.unusedClasses;

import travellingappraiser.unusedClasses.Path;

import java.util.ArrayList;

/**
 * Created by darrick on 5/9/16.
 */
public class PathFinder {

    /* matrix is an NxN matrix of N vertices of a directed graph,
    where row A, column B gives A -> B weight.

     */
    ArrayList<Path> paths = new ArrayList<Path>();


    public ArrayList<Path> analysis(int[][] matrix) {
        if(matrix[0].length != matrix.length) {
            System.out.print("Error, not a square matrix");
            return null;
        }
        int n = matrix.length;

        //have a matrix of NxN, let's start by solving for a directed matrix.
        matrix = directed2Undirected(matrix);

        /*Now, since we know that the upper right triangle of the matrix is now populated by
        distances, we need to sort through the triangle such that: each node is visited, and
        that some sort of optimization has been achieved.  Now we construct a list of every
        possible configuration of 5 nodes visited.  The for loop iterates over all permutations.
        I do need a case for if the nodes are not a multiple of 5.
         */
        for(int i = 1; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        for (int m = 0; m < n; m++) {
                            if (m == i || m == j || m == k || m == l) {
                                continue;
                            }
                            paths.add(
                                    new Path(
                                            new int[]{i, j, k, l, m},
                                            matrix[i][j] + matrix[j][k] + matrix[k][l] + matrix[l][m]
                                    ));
                        }
                    }
                }
            }
        }

        //wow, so paths now has all the configurations, now we sort distance:

        /*
        Collections.sort(paths);
        Path[] arrayPath = new Path[(int) n/5]; //cast to int might cause problems!!
        int j =0;
        arrayPath[0] = paths.get(0);
        for(int i=0;arrayPath[j]!=null;i++) {
            if(!arrayPath[j].conflicts(paths.get(i+1))) {
                j++;
                arrayPath[j] = paths.get(i+1);
            }
        }
        */

        /*now we have a an array (arrayPaths) which represents 5 min paths as found in
         order.  Now, theoretically, this would at least be a local min with few exceptions.
         Mainly, it gives us an upper bound on solutions.`
          */

        return paths;


    }

    private int[][] directed2Undirected(int[][] matrix) {
        int[][] Undirected = matrix;

        for(int i=0 ; i < matrix.length ; i++ ) {
            for(int j=0; j <= i; j++) {
                Undirected[i][j] = (int)( (matrix[i][j] + matrix[j][i]) / 2);
                Undirected[j][i] = Undirected[i][j];
            }

        }

        return Undirected;

    }


}
