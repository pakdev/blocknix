package blocknix.app;

import blocknix.Utils;
import blocknix.gui.MenuController;
import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import de.lessvoid.nifty.Nifty;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Peter Kurlak on 09/20/2014.
 */
public class ClientMain extends SimpleApplication {

    private Nifty _nifty;

    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        AppSettings settings = new AppSettings(true);
        app.setSettings(settings);
        app.start(JmeContext.Type.Display);
    }

    @Override
    public void simpleInitApp() {
        this.startNifty();
    }

    public void quit() {
        _nifty.gotoScreen("end");
    }

    private void startNifty() {
        MenuController menuController = new MenuController();
        stateManager.attach(menuController);

        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        guiViewPort.addProcessor(niftyDisplay);

        _nifty = niftyDisplay.getNifty();

        final String clientInterface = "Interface/Nifty/Client.xml";
        // TODO: disable on releases
        _nifty.setDebugOptionPanelColors(false);
        try {
            _nifty.validateXml(clientInterface);
        } catch (Exception ex) {
            Utils.log(ClientMain.class, Level.SEVERE, "Nifty XML is malformed: {0}", ex);
        }

        _nifty.fromXml(clientInterface, "start", menuController);
        flyCam.setDragToRotate(true);
    }
}
