/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs21120.assignment2018;

import java.util.Random;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * A simple JavaFX application to run the Countdown game
 *
 * @author bpt
 */
public class CountDownFX extends Application {
    /* TODO change this line to construct your IExpressionFactory */
    IExpressionFactory factory = new Expressionjot38();

    IExpression eq = null;
    Spinner<Integer> spinner = new Spinner<>();
    Label jLabel1 = new Label("Select how many large numbers then hit Go or Advanced:");
    Label jLabel2 = new Label("Target: ");
    Label jLabel3 = new Label("XXX");
    Button goButton = new Button();
    Button advancedButton = new Button();
    TextArea textArea = new TextArea();
    int[] largeNums = {25, 50, 75, 100};
    int[] smallNums = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10};

    @Override
    public void start(Stage primaryStage) {

        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 3);

        spinner.setValueFactory(valueFactory);
        goButton.setText("Go!");
        // Create a handler for the Go button
        // Uses a thread to show the countdown on the button
        goButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Random rand = new Random();

                int[] vals = new int[6];
                int numLarge = (int) spinner.getValue();
                int k = 0;

                // Select the large then small numbers
                TreeSet<Integer> lnums = new TreeSet<>();

                while (k < numLarge) {
                    int j = rand.nextInt(4);
                    if (lnums.contains(j)) continue;
                    vals[k++] = largeNums[j];
                    lnums.add(j);
                }
                lnums = new TreeSet<>();
                while (k < 6) {
                    int j = rand.nextInt(smallNums.length);
                    if (lnums.contains(j)) continue;
                    vals[k++] = smallNums[j];
                    lnums.add(j);
                }

                // Show the selected values
                String t = "";
                for (int i = 0; i < 6; i++) {
                    t += vals[i] + "   ";
                }
                textArea.setText(t);

                float target = 0;
                eq = null;

                // Create the random equation using the numbers
                // making sure that the result is in the right range
                // and that the countdown rules are followed
                while (eq == null) {
                    try {
                        eq = factory.createRandomEquation(vals);
                        System.out.println(eq);
                        target = eq.evaluateCountdown();
                        if (target < 100 || target > 999) eq = null;

                    } catch (Exception e) {
                        eq = null;
                    }
                }
                // Display the target value
                jLabel3.setText("" + target);

                // Create a new thread to do the countdown on the button
                Thread thr = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        goButton.setDisable(true);
                        advancedButton.setDisable(true);
                        long tstart = System.currentTimeMillis();
                        long tnow = System.currentTimeMillis();
                        while (tnow - tstart < 30000) {
                            long t = 30 - (tnow - tstart) / 1000;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    goButton.setText("" + t);
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                            }
                            tnow = System.currentTimeMillis();
                        }
                        String result = "\n" + eq.toString();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                textArea.appendText(result);

                                goButton.setText("Go!");
                                goButton.setDisable(false);
                                advancedButton.setDisable(false);
                            }
                        });

                    }

                });
                thr.start();

            }
        });

        advancedButton.setText("Advanced");

        // Create a handler for the Advanced button
        advancedButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Random rand = new Random();

                int[] vals = new int[6];
                int numLarge = (int) spinner.getValue();
                int k = 0;
                TreeSet<Integer> lnums = new TreeSet<>();
                // Select the large then small numbers to use
                while (k < numLarge) {
                    int j = rand.nextInt(4);
                    if (lnums.contains(j)) continue;
                    vals[k++] = largeNums[j];
                    lnums.add(j);
                }
                lnums = new TreeSet<>();
                while (k < 6) {
                    int j = rand.nextInt(smallNums.length);
                    if (lnums.contains(j)) continue;
                    vals[k++] = smallNums[j];
                    lnums.add(j);
                }

                String t = "";
                for (int i = 0; i < 6; i++) {
                    t += vals[i] + "   ";
                }
                textArea.setText(t);

                // Calculate the target 3 digit value
                int target = rand.nextInt(900) + 100;
                // Try to solve it using the available values
                eq = factory.findBestSolution(vals, target);
                // Display the target value
                jLabel3.setText("" + target);

                // Create a thread to do the countdown on the button
                Thread thr = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        goButton.setDisable(true);
                        advancedButton.setDisable(true);
                        long tstart = System.currentTimeMillis();
                        long tnow = System.currentTimeMillis();
                        while (tnow - tstart < 30000) {
                            long t = 30 - (tnow - tstart) / 1000;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    advancedButton.setText("" + t);
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                            }
                            tnow = System.currentTimeMillis();
                        }
                        String result = "\n" + eq.toString();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                textArea.appendText(result);

                                advancedButton.setText("Advanced");
                                goButton.setDisable(false);
                                advancedButton.setDisable(false);
                            }
                        });

                    }

                });
                thr.start();

            }
        });
        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(jLabel1, spinner, jLabel2, jLabel3, goButton, advancedButton, textArea);


        Scene scene = new Scene(root, 600, 300);

        primaryStage.setTitle("Countdown!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
