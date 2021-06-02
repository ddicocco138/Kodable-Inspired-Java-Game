/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damia
 */
public class Final_ProjectControllerTest {
    
        String Rectangle1; //Variables for testing
        String Rectangle2;
        String Rectangle3;
        String Rectangle4;
        String Awnser;
    
 
    
    public Final_ProjectControllerTest() {

        
    }
    
    @Test
    public void AwnserEntry(String pos1, String pos2, String pos3, String pos4)
    {
        Rectangle1 = pos1; // makes Rect1 = string entered pos1
        Rectangle2 = pos2; // makes Rect2 = string entered pos2
        Rectangle3 = pos3; // makes Rect3 = string entered pos3
        Rectangle4 = pos4; // makes Rect4 = string entered pos4
        assertEquals(pos1,Rectangle1); // tests if they actually equal
        assertEquals(pos2,Rectangle2);
        assertEquals(pos3,Rectangle3);
        assertEquals(pos4,Rectangle4);
    }
    
    @Test
    public void AwnserCheck()
    {
        //If the combo is correct, Awnser is correct, else it is Incorrect
        if (Rectangle1 == "Left" && Rectangle2 == "Right" && Rectangle3 == "Up" && Rectangle4 == "Down")
        {
            Awnser = "Correct";
        }
        else
            Awnser = "InCorrect";
    }
    
    
    @Test
    public void testMain() {
    }

    @Test
    public void testGetArrows() {
        //established the entered in awnser
        AwnserEntry("Left","Right","Up","Down");
        //checks if the awnser is right or wrong
        AwnserCheck();
        //given the enterd awnser, the expected value is correct
        String expectVal = "Correct";
        //checks if the expected value is correct, and if the awnser variable matches
        assertEquals(expectVal,Awnser);
    }

   
    
}