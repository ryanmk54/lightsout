package lightsout;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Main class used to start the game.
 * 
 * Puts all components in a JFrame and displays it.
 */
public class LightsOutGame {
    public static void main(String args[]) {
        // Initialize the StatusBar
        StatusBar statusBar = new StatusBar();

        // Initialize the LightPanel
        // The status bar will receive updates from the light panel
        // The light panel will have 5 rows and columns
        LightPanel lightPanel = new LightPanel(statusBar, 5, 5);
        lightPanel.randomize();

        // Add everything to the window and display it
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout());
        window.add(lightPanel);
            // Light Panel goes in the center
        window.add(statusBar, BorderLayout.SOUTH);
            // StatusBar goes on the bottom
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Lights Out");
		window.setVisible(true);
    }

}
