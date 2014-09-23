package blocknix.app;

import blocknix.Utils;
import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.JmeContext;
import de.lessvoid.nifty.Nifty;
import blocknix.gui.MainMenuController;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by pkurl_000 on 09/20/2014.
 */
public class ClientMain extends SimpleApplication {

    private Nifty nifty_;
    private Client client_;
    private MainMenuController mainMenuController_;

    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        app.start(JmeContext.Type.Display);
    }

    @Override
    public void simpleInitApp() {
        this.startNifty();

        try {
            client_ = Network.connectToServer("localhost", 7117);
            client_.start();
        }
        catch (IOException ex) {
            Utils.log(ClientMain.class, Level.SEVERE, "Cannot start client: {0}", ex.getMessage());
            return;
        }
    }

    public void quit() {
        nifty_.gotoScreen("end");
    }

    private void startNifty() {
        mainMenuController_ = new MainMenuController();
        stateManager.attach(mainMenuController_);

        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty_ = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);

        final String clientInterface = "Interface/Nifty/Client.xml";
        // TODO: disable on releases
        nifty_.setDebugOptionPanelColors(false);
        try {
            nifty_.validateXml(clientInterface);
        } catch (Exception ex) {
            Utils.log(ClientMain.class, Level.SEVERE, "Nifty XML is malformed: {0}", ex);
        }

        nifty_.fromXml(clientInterface, "start", mainMenuController_);
        flyCam.setDragToRotate(true);
    }
}
