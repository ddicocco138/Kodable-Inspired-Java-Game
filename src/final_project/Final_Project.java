/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * This the driver class for the Kodable game. It features a start method and a
 * main method
 *
 * @author Larkin Gustavson, Archit Shah, and Damiano Di Cocco
 * @version 1.0
 *
 * @see Final_ProjectController.java
 */
public class Final_Project extends Application {

    /**
     * The main method of the application
     *
     * @author Larkin Gustavson, Archit Shah, and Damiano Di Cocco
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        launch(args); // launch the command line arguments
    }

    /**
     *
     * @param stage the "window" of the application, the screen with what the
     * user will see.
     *
     * @throws Exception if the application is not able to load in the window.
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent group = FXMLLoader.load(getClass().getResource("Final_Project.fxml")); // load the FXML file

        Scene scene = new Scene(group); // create the scene, comprised of the group

        stage.setScene(scene); // set the scene on the stage

        stage.setResizable(false); // not allowing the user to resize the screen

        stage.sizeToScene(); // setting the size of the stage to be the scene 

        stage.setTitle("Kodable Level 1"); // set the title of the window to be Kodable

        stage.show(); // show what is on the stage

    }

}
