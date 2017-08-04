package lightsout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;


/**
 * The grid of lights
 */
public class LightPanel extends JPanel implements ActionListener{
    private final int numRows;
    private final int numCols;
    private final LightButton[][] lights;
    private final LightPanelUpdateHandler lightPanelUpdateHandler;

    /**
     * Creates a new LightPanel with the given handler, number of rows, and
     * columns
     * @param lightPanelUpdateHandler the class that will receive updates when a light is clicked
     * @param numRows the number of rows for this panel
     * @param numCols the number of columns for this panel
     */
    public LightPanel(final LightPanelUpdateHandler lightPanelUpdateHandler,
            final int numRows, final int numCols) {

        super(new GridLayout(numRows, numCols));
        this.lightPanelUpdateHandler = lightPanelUpdateHandler;
        this.numRows = numRows;
        this.numCols = numCols;
        lights = new LightButton[numRows][numCols];

        // Create the lights and add them to the light panel
        for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
                lights[row][col] = new LightButton(row, col);
				add(lights[row][col]);
				lights[row][col].addActionListener(this);
			}
		}

        // Set the initial size of the lights on startup
        final int lightButtonSize = 50;
        final int panelWidth = lightButtonSize*numRows;
        final int panelHeight = lightButtonSize*numCols;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }


    /**
     * Counts the number of lights left on
     * @return the number of lights left on
     */
    public int countNumLightsLeft() {
        int numLeft = 0;

        // Go through each light, and check if it is on
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                if(lights[row][col].isOn())
                    numLeft++;
            }
        }
        return numLeft;
    }


    /**
     * Toggles some of the lights on the board
     */
    public void randomize() {

        // Go through each light, only clicking some of them
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
				Random ranGen = new Random();
				if(ranGen.nextBoolean())
					clickLight(row, col);		
            }
        }

        int numLightsLeft = countNumLightsLeft();
        lightPanelUpdateHandler.handleLightPanelUpdate(numLightsLeft);
    }


    /**
     * Handles clicking on a light button
     * @param event the click event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() instanceof LightButton) {
            LightButton lightButton = (LightButton)event.getSource();
            clickLight(lightButton.rowNum, lightButton.colNum);
            int numLightsLeft = countNumLightsLeft();
            lightPanelUpdateHandler.handleLightPanelUpdate(numLightsLeft);
        }
    }


    /**
     * Happens when the user clicks a light button. 
     * Toggle the light and all surrounding lights
     * @param rowNum the row number of the light to click
     * @param colNum the column number of the light to click
     */
    private void clickLight(final int rowNum, final int colNum) {
        lights[rowNum][colNum].toggle();
        toggleSurroundingLights(rowNum, colNum);
    }


    /**
     * Toggles all the lights surrounding a particular light
     * @param rowNum the row number of the light to click
     * @param colNum the column number of the light to click
     */
    private void toggleSurroundingLights(final int rowNum, final int colNum) {
        // if this light isn't in the first row, 
        if(rowNum > 0)
            // toggle the light directly above this one
            lights[rowNum - 1][colNum].toggle();

        // if this light isn't in the last row, 
        if(rowNum < numRows - 1)
            // toggle the light directly below this one
            lights[rowNum + 1][colNum].toggle();

        // if this light isn't in the first column, 
        if(colNum >  0)
            // toggle the light directly to the left of this one
            lights[rowNum][colNum - 1].toggle();

        // if this light isn't in the last column,
        if(colNum < numCols - 1)
            // toggle the light directly to the right of this one
            lights[rowNum][colNum + 1].toggle();
    }
    
}