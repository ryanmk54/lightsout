package lightsout;

/**
 * The interface that receives updates for the light panel
 */
public interface LightPanelUpdateHandler {
    public void handleLightPanelUpdate(int numLightsOn); 
}
