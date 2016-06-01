package GUI;

import apiClientInterface.InitializeData;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import travellingappraiser.geneticAlgorithm.GAParameters;
import travellingappraiser.geneticAlgorithm.RunGA;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by darrick on 5/23/16.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<String> items;

    //construct a url for google maps to display a circuit
    public void googleWaypoints(int[] locs) throws URISyntaxException, IOException {

        String uriString = "http://maps.google.com/maps?";

        uriString += "saddr=" + items.get(0).replace(' ','+');
        uriString += "daddr=" + items.get(locs[0]).replace(' ','+');

        for (int i = 1; i < locs.length; i++) {
            uriString += "+to:" + items.get(locs[i]).replace(' ', '+');
        }
        uriString += "+to:" + items.get(0).replace(' ','+');

        URI uri = new URI(uriString);
        Desktop.getDesktop().browse(uri);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        final Text txt = new Text("Home Address");

        //Defining the Home Address text field
        final TextField homeaddr = new TextField();
        homeaddr.setPromptText("Enter your home address");
        homeaddr.setPrefColumnCount(16);
        homeaddr.setTooltip(new Tooltip("Enter a home address"));

        //Defining the Destination text field
        final TextField destaddr = new TextField();
        destaddr.setPromptText("Enter a destination address");
        destaddr.setPrefColumnCount(16);
        destaddr.setTooltip(new Tooltip("enter all locations here in any order"));

        //create a list for containing submitted addresses
        final ArrayList addresses = new ArrayList();
        final ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (addresses);
        list.setItems(items);

        Button submit = new Button("Submit Addresses");

        Button addAddress = new Button("Add Address");

        Button removeAddress = new Button("Remove Address");

        final Text headertxt = new Text("Options:");

        final CheckBox dirOption = new CheckBox("Ignore travel time asymmetry");
        dirOption.setTooltip(new Tooltip(
                        "Averages X->Y and Y->X travel times. I am\n" +
                        "yet unsure of how this might affect results."));

        final CheckBox oriOption = new CheckBox("Ignore home travel times");
        oriOption.setTooltip(new Tooltip(
                        "Sets the weight of all Home->X and X->Home\n" +
                        "travel times to zero. The algorithm will find\n" +
                        "houses which are close together, rather than\n" +
                        "finding an overall minimized travel time.\n\n" +
                        "HOME ADDRESS STILL REQUIRED"));

        final TextField route = new TextField();
        route.setPromptText("Enter Custom Route");
        route.setTooltip(new Tooltip(
                        "Enter a list of numbers which add up to the\n" +
                        "number of locations to visit. Each represents\n" +
                        "a path length. e.g.: 5,5,5,4,4"));

        Tooltip runAble = new Tooltip("");
        Tooltip notRunAble = new Tooltip("Address info not yet submitted");
        final Button run = new Button("Run");
        run.setDisable(true);
        run.setTooltip(notRunAble);


        //Event handlers
        destaddr.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.ENTER) {
                if(!destaddr.getText().isEmpty()) {
                    items.add(destaddr.getText());
                    list.setItems(items);
                    destaddr.clear();
                }
            }
        });
        addAddress.setOnAction(event -> {
            if(!destaddr.getText().isEmpty()) {
                items.add(destaddr.getText());
                list.setItems(items);
                destaddr.clear();
            }
        });
        removeAddress.setOnAction(event -> {
            if(list.getFocusModel().getFocusedIndex()!=-1) {
                items.remove(list.getFocusModel().getFocusedIndex());
                list.setItems(items);
            }
        });
        submit.setOnAction(event-> {
            GAParameters.setTotalLocations(items.size());
            items.add(items.get(0));
            items.set(0,homeaddr.getText());
            InitializeData.main(items.toArray(new String[0]));
            run.setDisable(false);
            homeaddr.setDisable(true);
            destaddr.setDisable(true);
            addAddress.setDisable(true);
            removeAddress.setDisable(true);
            submit.setDisable(true);
        });
        run.setOnAction(event -> {
            GAParameters.setMatrixUndirected(dirOption.isSelected());
            GAParameters.setNoOriginWeight(oriOption.isSelected());

            //checks for an input in custom route, and enters it.
            if(!route.getText().isEmpty()) {
                String[] numberStrs = route.getText().split("[ ,]+");

                int[] numbers = new int[numberStrs.length];
                for(int i = 0;i < numberStrs.length;i++) {
                    numbers[i] = Integer.parseInt(numberStrs[i]);
                }

                int total =0;
                for (int k: numbers) { total+=k; }
                if (total==GAParameters.getTotalLocations()) {
                    GAParameters.setCustomRoute(true);
                    GAParameters.setCustomRouteArray(numbers);
                    GAParameters.setROUTES(numbers.length);
                } else {
                    System.out.print("Error with custom route, check that the sum = locations to visit");
                }
            }
            run.setDisable(false);
            new RunGA().run();
            run.setDisable(true);
        });

        Pane mylayout = new Pane();
        txt.setLayoutX(10);
        txt.setLayoutY(20);
        list.setLayoutY(10);
        list.setLayoutX(240);
        homeaddr.setLayoutX(10);
        homeaddr.setLayoutY(30);
        destaddr.setLayoutX(10);
        destaddr.setLayoutY(70);
        addAddress.setLayoutX(10);
        addAddress.setLayoutY(100);
        removeAddress.setLayoutX(10);
        removeAddress.setLayoutY(130);
        headertxt.setLayoutX(10);
        headertxt.setLayoutY(190);
        dirOption.setLayoutX(10);
        dirOption.setLayoutY(200);
        oriOption.setLayoutX(10);
        oriOption.setLayoutY(230);
        route.setLayoutX(10);
        route.setLayoutY(260);
        run.setLayoutX(218);
        run.setLayoutY(480);
        submit.setLayoutX(293);
        submit.setLayoutY(420);
        mylayout.getChildren().addAll(txt,list,homeaddr,destaddr,addAddress,
                removeAddress,submit,headertxt,dirOption,oriOption,route,run);
        Scene myscene = new Scene(mylayout, 500, 530);
        primaryStage.setTitle("Travelling Appraiser");
        primaryStage.setScene(myscene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}