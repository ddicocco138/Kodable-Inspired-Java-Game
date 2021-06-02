/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Larkin Gustavson, Archit Shah, and Damiano Di Cocco
 * @version 1.0
 * @see Final_Project.java
 *
 */
public class Final_ProjectController implements Initializable {

    @FXML
    private ImageView grassBackgroundImage; // used for the grass in the background
    @FXML
    private ImageView woodenBackgroundImage; // used for the wood in the background to show the actions that the user chose
    @FXML
    private ImageView greyRectangleBackground; // used for the grey rectangle in the background to either play the actions that the chose or to stop
    @FXML
    private ImageView goldenRectangleBackground; // used for the rectangle to display possible directions that the character can move 
    @FXML
    private Rectangle position1; // used to represent the first rectangle to hold a direction, chosen by the user
    @FXML
    private Rectangle position2; // used to represent the first rectangle to hold a direction, chosen by the user
    @FXML
    private Rectangle position3; // used to represent the first rectangle to hold a direction, chosen by the user
    @FXML
    private Rectangle position4; // used to represent the first rectangle to hold a direction, chosen by the user
    @FXML
    private Button startButton; // used to represent the play button, it will start the animation once clicked
    @FXML
    private Circle player; // used to represent the player and will be animated (RotateTransition and TranslateTransition)
    @FXML
    private Button pauseButton; // used to represent the pause button, to stop an animation by the user
    @FXML
    private AnchorPane anchorPane; // used to be able to put a background image in the rectangle (where the user chooses the direction to move and where the arrow indicating the direction will be shown)

    private Path path; // creates the path object

    private MoveTo moveTo; // represents the starting point of the path

    private LineTo line1; // representing the path from moveTo to line1
    private LineTo line2; // representing the path from moveTo to line1
    private LineTo line3; //representing the path from moveTo to line3
    private LineTo line4; // representing the path from moveTo to line4

    private PathTransition pathTransition; // moves along the previously created path object

    private RotateTransition rotateTransition; // this is used for the ability of the circle (player) to rotate

    private String Rectangle1 = "NULL"; // this will be modified later to see what direction the user chose 
    private String Rectangle2 = "NULL"; // this will be modified later to see what direction the user chose
    private String Rectangle3 = "NULL"; // this will be modified later to see what direction the user chose
    private String Rectangle4 = "NULL"; // this will be modified later to see what direction the user chose

    private boolean isPaused = true; // used to indicate if the game is paused

    private MenuItem menuItem1; // used to represent the submenu with in a context menu (representing directions the user can choose). A context menu is when the user right clicks in the rectangle, will represent Left
    private MenuItem menuItem2; // used to represent the submenu with in a context menu (representing directions the user can choose). A context menu is when the user right clicks in the rectangle, will represent Right
    private MenuItem menuItem3; // used to represent the submenu with in a context menu (representing directions the user can choose). A context menu is when the user right clicks in the rectangle, will represent Up
    private MenuItem menuItem4; // used to represent the submenu with in a context menu (representing directions the user can choose). A context menu is when the user right clicks in the rectangle, will represent Down

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * A method to allow a user to "right click" on a rectangle to make a
     * movement selection (up, down, left, or right).
     *
     * @param event the event represents the user using the right click on their
     * mouse.
     */
    @FXML
    private void rightClickRectangle1(MouseEvent event) {
        System.out.println("Rectangle 1 clicked"); // used for debugging purposes

        ContextMenu contextMenu = new ContextMenu(); // creating the context menu (right clicking)

        menuItem1 = new MenuItem("Left"); // the first menu item will contain the option for left
        menuItem2 = new MenuItem("Right"); // the second menu item will contain the option for right
        menuItem3 = new MenuItem("Up"); // the third menu item will contain the option for up
        menuItem4 = new MenuItem("Down"); // the fourth menu item will contain the option for down

        contextMenu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4); // add all of the menu items to the context menu

        menuItem1.setOnAction((ActionEvent e) -> { // when a user clicks on the first menu item (left), do the following
            Rectangle1 = "Left"; // since we know this menu item will represent the left, set the direction to be the left, since to get to her you had to click on the menu item titled "left"

            System.out.println("Left"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a left arrow
            rectangle.setX(20); // setting the rectangle's x postion to be 20
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/LeftArrowButton.jpg"); // store the left arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 
            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem2.setOnAction((ActionEvent e) -> { // on clicking this menu item (right)
            Rectangle1 = "Right"; // since we know this menu item will represent the right, set the direction to be the right, since to get to her you had to click on the menu item titled "right"

            System.out.println("Right"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a right arrow
            rectangle.setX(20); // setting the rectangle's x postion to be 20
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120);  // setting the rectangle's height to be 120

            Image image = new Image("final_project/RightArrowButton.jpg"); // store the right arrow when the user chooses the right from the drop down as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 
            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem3.setOnAction((ActionEvent e) -> { // on clicking this menu item (up)
            Rectangle1 = "Up"; // since we know this menu item will represent the up direction, set the direction to be the up direction, since to get to her you had to click on the menu item titled "up"

            System.out.println("Up"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a up arrow
            rectangle.setX(20); // setting the rectangle's x postion to be 20
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/UpArrowButton.jpg"); // used to store the up arrow for when a user chooses the up button from the context menu and store it as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem4.setOnAction((ActionEvent e) -> { // on clicking this menu item (down)
            Rectangle1 = "Down"; // since we know this menu item will represent the down, set the direction to be the down direction, since to get to her you had to click on the menu item titled "down"

            System.out.println("Down"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a down arrow
            rectangle.setX(20); // setting the rectangle's x postion to be 20
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/DownArrowButton.jpg"); // used to store the down arrow for when a user chooses the down button from the context menu and store it as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        position1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { // used to add the ability to righ click in the rectangle 
            @Override
            public void handle(MouseEvent t) { // for when the user clicks
                if (t.getButton() == MouseButton.SECONDARY) { // secondary means right click on the mouse wheel or mouse pad
                    contextMenu.show(position1, t.getScreenX(), t.getScreenY()); // show the context menu on the rectangle, where the person right clicked. 
                }
            }
        });

    }

    /**
     * This method will allow the user to right click on the second rectangle to
     * select one of four directions (left, right, up, and down), by using their
     * mouse.
     *
     * @param event represents the mouse click from the user.
     */
    @FXML
    private void rightClickRectangle2(MouseEvent event) {
        System.out.println("Rectangle 2 clicked"); // used for debugging 

        ContextMenu contextMenu2 = new ContextMenu(); // adding the context menu

        menuItem1 = new MenuItem("Left"); // the first menu item will contain the option for left
        menuItem2 = new MenuItem("Right"); // the second menu item will contain the option for right
        menuItem3 = new MenuItem("Up"); // the third menu item will contain the option for up
        menuItem4 = new MenuItem("Down"); // the fourth menu item will contain the option for down

        contextMenu2.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4); // add all of the menu items to the context menu

        menuItem1.setOnAction((ActionEvent e) -> { // on clicking this menu item (left)
            Rectangle2 = "Left"; // since we know this menu item will represent the left, set the direction to be the left, since to get to her you had to click on the menu item titled "left"

            System.out.println("Left"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a left arrow
            rectangle.setX(172); // setting the rectangle's x postion to be 172
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/LeftArrowButton.jpg"); // store the left arrow as an image, to be used later as a background

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem2.setOnAction((ActionEvent e) -> { // on clicking this menu item (right)
            Rectangle2 = "Right"; // since we know this menu item will represent the right, set the direction to be the right, since to get to her you had to click on the menu item titled "right"

            System.out.println("Right"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a right arrow
            rectangle.setX(172); // setting the rectangle's x postion to be 172
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/RightArrowButton.jpg"); // store the right arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem3.setOnAction((ActionEvent e) -> { // on clicking this menu item (up)
            Rectangle2 = "Up"; // since we know this menu item will represent the up direction, set the direction to be the up direction, since to get to her you had to click on the menu item titled "up"

            System.out.println("Up"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a up arrow
            rectangle.setX(172); // setting the rectangle's x postion to be 172
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/UpArrowButton.jpg"); // store the up arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem4.setOnAction((ActionEvent e) -> { // on clicking this menu item (down)
            Rectangle2 = "Down"; // since we know this menu item will represent the down direction, set the direction to be the down direction, since to get to her you had to click on the menu item titled "down"

            System.out.println("Down"); // used for debugging purposes
            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a down arrow 
            rectangle.setX(172); // setting the rectangle's x postion to be 172
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/DownArrowButton.jpg"); // store the down arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        position2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { // used to add the ability to righ click in the rectangle (normally isn't possible)
            @Override
            public void handle(MouseEvent t) { // for when the user clicks
                if (t.getButton() == MouseButton.SECONDARY) { // secondary means right click on the mouse wheel or mouse pad
                    contextMenu2.show(position2, t.getScreenX(), t.getScreenY()); // show the context menu on the rectangle, where the person right clicked. 
                }
            }
        });

    }

    /**
     * This method will move the player (circle) across a map if the user
     * entered the correct sequence (left, right, up, and down), by using the
     * users mouse.
     *
     * @param event this will represent the mouse click from the user.
     * @throws IOException an error for writing to the file.
     */
    @FXML
    private void movePlayer(MouseEvent event) throws IOException {
        Date date = Calendar.getInstance().getTime(); // gets the current date and time

        System.out.println("Rectangle 1 is " + Rectangle1); // showing what initial value that was stored in the first rectangle
        System.out.println("Rectangle 2 is " + Rectangle2); // showing what initial value that was stored in the second rectangle
        System.out.println("Rectangle 3 is " + Rectangle3); // showing what initial value that was stored in the third rectangle
        System.out.println("Rectangle 4 is " + Rectangle4); // showing what initial value that was stored in the fourth rectangle

        FileWriter fileWriter = new FileWriter("log.txt", true); // sets the writer to not overwrite the log file if created already
        try (PrintWriter printWriter = new PrintWriter(fileWriter) // sets printWriter to write to the file set in fileWriter, the log, a try catch with resources, meaning it will automatically close the file 
                ) {
            printWriter.print(date); // starts the log line with current date and time
            printWriter.append(" - Level 1 : " + Rectangle1 + " " + Rectangle2 + " " + Rectangle3 + " " + Rectangle4 + " hit Start");
            printWriter.println(); // adds the information to the log line, then makes a new line for next log to start
        } 

        if (Rectangle1.equals("Down") && Rectangle2.equals("Right") && Rectangle3.equals("Up") && Rectangle4.equals("Right")) { // if the user enterred the correct path for this level
            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a corrct check mark
            rectangle.setX(800); // setting the rectangle's x postion to be 800
            rectangle.setY(15); // setting the rectangle's y postion to be 15
            rectangle.setWidth(175); // setting the rectangle's width to be 175
            rectangle.setHeight(175); // setting the rectangle's width to be 175

            Image image = new Image("final_project/correctButton.jpg"); // used to store the image used to be used if the user got the correct sequence as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the correct check mark picture 

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), rectangle); // make the fade transtion last for 3 seconds and apply the animation to the rectangle
            fadeTransition.setFromValue(1.0); // starts at full opacity (you can see it)
            fadeTransition.setToValue(0.0); // it will end at zero opacity (you can't see it)
            fadeTransition.setCycleCount(1); // only does this one time
            fadeTransition.setAutoReverse(false); // the cycle doesn't repeat more then once

            fadeTransition.play(); // play the fade transtition animation to the screen

            if (isPaused == true) { // if the game is paused

                path = new Path(); // create a new path

                moveTo = new MoveTo(0, 0); // move to position (0, 0), where the circle (player) is 

                line1 = new LineTo(0, 300); // move from start to point line1

                line2 = new LineTo(414, 300); // move from start to point line2

                line3 = new LineTo(410, 0); // move from start to point line3

                line4 = new LineTo(832, 0); // move from start to point line4

                path.getElements().addAll(moveTo, line1, line2, line3, line4); // adding all of the elements of the path

                pathTransition = new PathTransition(); // creating the ability to move on the previously created path

                pathTransition.setDuration(Duration.millis(10000)); // set the duration of the path transition to be 10 seconds 

                pathTransition.setNode(player); // make the player (the circle) the one that will be moving along the path

                pathTransition.setPath(path); // moving along this path

                rotateTransition = new RotateTransition(new Duration(10000), player); // make the rotate transtion animation last for 10 seconds and apply it to the player
                rotateTransition.setFromAngle(0); // start rotating from 0 degrees
                rotateTransition.setToAngle(180); // stop roating at 180 degrees

                pathTransition.play(); // play the path transition animation to the screen
                rotateTransition.play(); // play the rotate transition animation to the screen 

                isPaused = false; // the game is no longer paused

                Image img = new Image("final_project/winnerButton.jpg"); // pastes the custom image onto the button

                ImageView view = new ImageView(img);  // allows the image to be pasted onto the button
                view.setFitHeight(300); // sets the height for the image on button
                view.setPreserveRatio(true); // makes sure the image is the correct resolution / measurements

                Button nextLevel = new Button(); // create a button that allows the player to move onto the next level
                nextLevel.setLayoutX(1320); // position x for button
                nextLevel.setLayoutY(300); // position y for button
                nextLevel.setMinWidth(100); // width of the button
                nextLevel.setMinHeight(100); // height of the button
                nextLevel.setGraphic(view); // sets the image that will be pasted onto the button
                nextLevel.setOnAction((ActionEvent e) -> {
                    try { // button action for next level

                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Final_Project_Level2.fxml")); // load up the next FXML file

                        Scene tableViewScene = new Scene(tableViewParent); // sets the fxml as the scene

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // makes the window the stage, and the scene
                        window.setScene(tableViewScene); // sets the scene to be the new fxml
                        window.setTitle("Kodable Level 2");
                        window.show(); // shows the next fxml file in the window
                    } catch (IOException ex) { // needed in order to implement this solution
                        Logger.getLogger(Final_ProjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                anchorPane.getChildren().add(nextLevel); // add the button to the anchorPane that will display when the arrows are correct

            } else { // if the game is not paused
                pathTransition.play(); // play the path transition animation to the screen
                rotateTransition.play(); // play the rotate transition animation to the screen 

            }

        } else { // if the path entered by the user is incorrect, do the following
            System.out.println("INCORRECT!!"); // used for debugging

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a x for an incorrect sequence
            rectangle.setX(800); // setting the rectangle's x postion to be 800
            rectangle.setY(15); // // setting the rectangle's y postion to be 15
            rectangle.setWidth(175); // setting the rectangle's width to be 175
            rectangle.setHeight(175); // setting the rectangle's height to be 175

            Image image = new Image("final_project/xButton.jpg"); // store the incorrect x as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), rectangle); // make the fade transtion last for 3 seconds and apply the animation to the rectangle
            fadeTransition.setFromValue(1.0); // starts at full opacity (you can see it)
            fadeTransition.setToValue(0.0); // it will end at zero opacity (you can't see it)
            fadeTransition.setCycleCount(1); // only does this one time
            fadeTransition.setAutoReverse(false); // the cycle doesn't repeat more then once

            fadeTransition.play(); // play the fade transtition animation on the screen
        }
    }

    /**
     * A method to allow a user to "right click" on a rectangle to make a
     * movement selection (up, down, left, or right), by using the users mouse.
     *
     * @param event the event represents the user using the right click on their
     * mouse or trackpad.
     */
    @FXML
    private void rightClickRectangle3(MouseEvent event) {
        System.out.println("Rectangle 3 clicked");

        ContextMenu contextMenu3 = new ContextMenu(); // adding the context menu

        menuItem1 = new MenuItem("Left"); // the first menu item will contain the option for left
        menuItem2 = new MenuItem("Right"); // the second menu item will contain the option for right
        menuItem3 = new MenuItem("Up"); // the third menu item will contain the option for up
        menuItem4 = new MenuItem("Down"); // the fourth menu item will contain the option for down

        contextMenu3.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4); // add all of the menu items to the context menu

        menuItem1.setOnAction((ActionEvent e) -> { // on clicking this menu item (left)
            Rectangle3 = "Left"; // since we know this menu item will represent the left direction, set the direction to be the left direction, since to get to her you had to click on the menu item titled "left"

            System.out.println("Left"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a left arrow
            rectangle.setX(319); // setting the rectangle's x postion to be 319
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/LeftArrowButton.jpg"); // store the left arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem2.setOnAction((ActionEvent e) -> { // on clicking this menu item (right)
            Rectangle3 = "Right"; // since we know this menu item will represent the right direction, set the direction to be the right direction, since to get to her you had to click on the menu item titled "right"

            System.out.println("Right"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a right arrow
            rectangle.setX(319); // setting the rectangle's x postion to be 319
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/RightArrowButton.jpg"); // store the right arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem3.setOnAction((ActionEvent e) -> { // on clicking this menu item (up)
            Rectangle3 = "Up"; // since we know this menu item will represent the up direction, set the direction to be the up direction, since to get to her you had to click on the menu item titled "up"

            System.out.println("Up"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a up arrow
            rectangle.setX(319); // setting the rectangle's x postion to be 319
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/UpArrowButton.jpg"); // store the up arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem4.setOnAction((ActionEvent e) -> { // on clicking this menu item (down)
            Rectangle3 = "Down"; // since we know this menu item will represent the down direction, set the direction to be the down direction, since to get to her you had to click on the menu item titled "down"

            System.out.println("Down"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a down arrow
            rectangle.setX(319); // setting the rectangle's x postion to be 319
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/DownArrowButton.jpg"); // store the down arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        position3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { // used to add the ability to righ click in the rectangle (normally isn't possible)
            @Override
            public void handle(MouseEvent t) { // for when the user clicks
                if (t.getButton() == MouseButton.SECONDARY) { // secondary means right click on the mouse wheel or mouse pad
                    contextMenu3.show(position3, t.getScreenX(), t.getScreenY()); // show the context menu on the rectangle, where the person right clicked. 
                }
            }
        });

    }

    /**
     * This method will allow the user to right click on the fourth rectangle to
     * select one of four directions (left, right, up, and down) by using the
     * users mouse.
     *
     * @param event represents the mouse click from the user
     */
    @FXML
    private void rightClickRectangle4(MouseEvent event) throws IOException {
        System.out.println("Rectangle 4 clicked"); // used for debugging

        ContextMenu contextMenu4 = new ContextMenu(); // adding the context menu

        menuItem1 = new MenuItem("Left"); // the first menu item will contain the option for left
        menuItem2 = new MenuItem("Right"); // the second menu item will contain the option for right
        menuItem3 = new MenuItem("Up"); // the third menu item will contain the option for up
        menuItem4 = new MenuItem("Down"); // the fourth menu item will contain the option for down

        contextMenu4.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4); // add all of the menu items to the context menu

        menuItem1.setOnAction((ActionEvent e) -> { // on clicking this menu item (left)
            Rectangle4 = "Left"; // since we know this menu item will represent the left direction, set the direction to be the left direction, since to get to her you had to click on the menu item titled "left"

            System.out.println("Left"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a left arrow
            rectangle.setX(475); // setting the rectangle's x postion to be 475
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/LeftArrowButton.jpg"); // store the left arrow when the user chooses the left from the drop down as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem2.setOnAction((ActionEvent e) -> { // on clicking on this menu item (right)
            Rectangle4 = "Right"; // since we know this menu item will represent the right direction, set the direction to be the right direction, since to get to her you had to click on the menu item titled "tight"

            System.out.println("Right"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a right arrow
            rectangle.setX(475); // setting the rectangle's x postion to be 475
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/RightArrowButton.jpg"); // store the right arrow when the user chooses the right from the drop down as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem3.setOnAction((ActionEvent e) -> { // on clicking this menu item (up)
            Rectangle4 = "Up"; // since we know this menu item will represent the up direction, set the direction to be the up direction, since to get to her you had to click on the menu item titled "up"

            System.out.println("Up"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a up arrow
            rectangle.setX(475); // setting the rectangle's x postion to be 475
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/UpArrowButton.jpg"); // store the up arrow when the user chooses the up from the drop down as an image

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        menuItem4.setOnAction((ActionEvent e) -> { // on clicking this menu item (down)
            Rectangle4 = "Down"; // since we know this menu item will represent the down direction, set the direction to be the down direction, since to get to her you had to click on the menu item titled "down"

            System.out.println("Down"); // used for debugging purposes

            Rectangle rectangle = new Rectangle(); // creating a new rectangle to store the background image of the picture of a down arrow
            rectangle.setX(475); // setting the rectangle's x postion to be 475
            rectangle.setY(39); // setting the rectangle's y postion to be 39
            rectangle.setWidth(120); // setting the rectangle's width to be 120
            rectangle.setHeight(120); // setting the rectangle's height to be 120

            Image image = new Image("final_project/DownArrowButton.jpg"); // store the down arrow as an image to be used as the background for whatever the user selects

            rectangle.setFill(new ImagePattern(image)); // filling the background with the image as the background
            rectangle.setMouseTransparent(true); // used to allow the user to chose a different direction if they chose the incorrect sequence, otherwise they would not be able to 

            anchorPane.getChildren().add(rectangle); // add the image to the anchorPane that will display the arrow the user chose
        });

        position4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { // used to add the ability to righ click in the rectangle (normally isn't possible)
            @Override
            public void handle(MouseEvent t) { // for when the user clicks
                if (t.getButton() == MouseButton.SECONDARY) { // secondary means right click on the mouse wheel or mouse pad
                    contextMenu4.show(position4, t.getScreenX(), t.getScreenY()); // show the context menu on the rectangle, where the person right clicked. 
                }
            }
        });

    }

    /**
     * This method will pause the player, where ever they are and are able to
     * resume from the spot they paused by then pressing the "START" button
     *
     * @param event representing the clicking of the mouse from the user
     */
    @FXML
    private void pausePlayer(MouseEvent event) throws IOException {

        pathTransition.pause(); // pausing the animation that make the circle move across the path

        rotateTransition.pause(); // pausing the animation that makes the circle roll across the path 

    }

}
