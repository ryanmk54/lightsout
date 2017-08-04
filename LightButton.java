/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsout;

import java.awt.Color;
import javax.swing.JButton;

/**
 * A single light in the light panel
 * @author ryan
 */
public class LightButton extends JButton {
    private final Color OFF_COLOR = Color.WHITE;
    private final Color ON_COLOR = Color.YELLOW;
    private boolean on = false;

    // Used in the actionPerformed method
    public final int rowNum;
    public final int colNum;


    /**
     * Creates a new light with the given row and column number
     * @param rowNum the row number in the LightPanel
     * @param colNum the column number in the LightPanel
     */
    public LightButton(final int rowNum, final int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        updateAppearance();
    }

    /**
     * Updates the background color of the light button
     * based on whether it is on or off
     */
    private void updateAppearance() {
        if (on) {
            setBackground(ON_COLOR);
        } else {
            setBackground(OFF_COLOR);
        }
    }


    /**
     * Toggles a single light button
     * 
     * Toggling the surrounding buttons is handled in the LightPanel class
     */
    public void toggle() {
        on = !on;
        updateAppearance();
    }

    /**
     * Returns true if this light is on
     * 
     * Used in LightPanel countNumLightLeft method
     * @return true if this light is on
     */
    public boolean isOn() {
        return on;
    }
}
