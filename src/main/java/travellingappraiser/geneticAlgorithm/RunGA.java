package travellingappraiser.geneticAlgorithm;

import apiClientInterface.MatrixFactory;
import org.graphstream.ui.view.Viewer;
import travellingappraiser.graphicalDisplay.GraphStreamGraph;

import static travellingappraiser.geneticAlgorithm.GAParameters.*;

/**
 * Created by darrick on 5/14/16.
 */
public class RunGA implements Runnable {

    private Tour bestTour;
    private GraphStreamGraph graph;


    public void run() {

        Population pop = new Population(populationSize, true);

        int[][] paramMatrix = getDistanceMatrix().clone();
        if (isMatrixUndirected()) {
            paramMatrix = MatrixFactory.Directed2Undirected(paramMatrix);
        }
        if(isNoOriginWeight()) {
            paramMatrix = MatrixFactory.removeOriginWeight(paramMatrix);
        }
        FitnessCalc.setDistanceMatrix(paramMatrix);

        bestTour = pop.getFittest();
        System.out.println("Initial fitness = " + pop.getFittest().getFitness()
                + " Distance: " + -pop.getFittest().getFitness());

        graph = new GraphStreamGraph(geocoordinates, bestTour);

        graph.startGraph();
        graph.updateEdges();
        Viewer viewer = graph.graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);

        for (int i = 0; i < generations/100; i++) {
            for (int j = 0; j < 100; j++) {
                pop = Algorithm.evolvePopulation(pop);
            }
            bestTour = pop.getFittest();
            graph.setTour(bestTour);
            graph.updateEdges();
        }

        System.out.println("Final fitness: "
                + pop.getFittest().getFitness() + " Distance: " + -pop.getFittest().getFitness());

        System.out.println(pop.getFittest().toString());
    }

}
