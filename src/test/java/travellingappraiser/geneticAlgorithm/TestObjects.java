package travellingappraiser.geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.sqrt;

/**
 * Created by darrick on 5/10/16.
 */
public class TestObjects {

    public static int[][] randDistanceMatrix(double[][] locations) {
        return locs2Dist(locations);
    }

    public static double[][] randMatrix(int n) {


        double[][] matrix = new double[n][2];

        int j = 0;

        for(int i=0;i<n;i++) {
            matrix[i][j] = random()*4000;
            if ((j == 0) && (i == (n - 1))) {i=-1;j=1;}

        }

        return matrix;

    }

    public static int[][] locs2Dist(double[][] locations) {
        int n = locations.length;

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                matrix[i][j] = distance(locations[i],locations[j]);
                matrix[j][i] = matrix[i][j];

            }
        }

        return matrix;

    }

    public static int distance(double[] a, double[] b) {
        return (int) sqrt(pow(a[0]-b[0],2) + pow(a[1]-b[1],2));
    }

    public static void main(String[] args) {
        double[][] matrix = randMatrix(35);
        System.out.print(Arrays.toString(matrix[0]));

        int[][] distmatrix = locs2Dist(matrix);
        System.out.print(Arrays.toString(distmatrix[0]));



    }

}
