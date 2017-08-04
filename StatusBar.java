package lightsout;

import java.awt.Color;
import javax.swing.*;


/**
 * The status bar that shows how many light the user has left
 */
public class StatusBar extends JLabel implements LightPanelUpdateHandler {
    public StatusBar() {
        
        // set the panel opaque so setting the background yellow below will work
        setOpaque(true);
    }


    /**
     * Update Handler for the light panel
     * 
     * Displays the number of lights left or "You Won!!!" 
     * if there aren't any lights left to turn off
     * @param numLightsOn 
     */
    @Override
    public void handleLightPanelUpdate(int numLightsOn) {

        // If there are more lights left, say how many.
        if(numLightsOn > 0) {
            setText(numLightsOn + " lights left");
            setBackground(Color.WHITE);
        } 
        // Otherwise, they won. Congratulate them.
        else {
            setText("You Won!!!");
            setBackground(Color.YELLOW);
        }
    }

}
