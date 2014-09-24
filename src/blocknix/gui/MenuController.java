package blocknix.gui;

import blocknix.Utils;
import blocknix.app.ClientMain;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Peter Kurlak on 9/20/2014.
 */
public class MenuController extends AbstractAppState implements ScreenController {

    private Nifty _nifty;
    private Screen _screen;
    private Client _client;
    private Application _app;
    private AppStateManager _stateManager;
    private List<DisplayMode> _displayModes;

    public void startGame() {
        Utils.logInfo(MenuController.class, "starting game");
    }

    public void joinServer() {
        Utils.logInfo(MenuController.class, "joining server");

        try {
            _client = Network.connectToServer("localhost", 7117);
            _client.start();
        }
        catch (IOException ex) {
            Utils.logSevere(MenuController.class, "Cannot start client: %s", ex.getMessage());
        }
    }

    public void buildStuff() {
        Utils.logInfo(MenuController.class, "building stuff");
    }

    public void setOptions() {
        Utils.logInfo(MenuController.class, "setting options");
        _nifty.gotoScreen("options");
    }

    public void applyOptions() {
        Utils.logInfo(MenuController.class, "applying options");

        DropDown<DisplayMode> resolutionsDropDown = this.getDropDown("resolutions");
        DisplayMode mode = _displayModes.get(resolutionsDropDown.getSelectedIndex());

        AppSettings settings = new AppSettings(true);
        settings.setResolution(mode.getWidth(), mode.getHeight());
        settings.setFrequency(mode.getRefreshRate());

        _app.setSettings(settings);
        _app.restart();

        _nifty.gotoScreen("start");
    }

    public void quitGame() {
        Utils.logInfo(MenuController.class, "quitting game");
        _app.stop();
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        _nifty = nifty;
        _screen = screen;

        this.populateResolutionDropDown();
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        _app = app;
        _stateManager = stateManager;
    }

    private DropDown getDropDown(String elementName) {
        return _nifty.getCurrentScreen().findNiftyControl(elementName, DropDown.class);
    }

    private void populateResolutionDropDown() {
        DropDown resolutionsDropDown = this.getDropDown("resolutions");

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        _displayModes = Arrays.asList(device.getDisplayModes());
        _displayModes.sort(new Comparator<DisplayMode>() {
            @Override
            public int compare(DisplayMode mode1, DisplayMode mode2) {
                int mode1Val = mode1.getWidth() + mode1.getHeight();
                int mode2Val = mode2.getWidth() + mode2.getHeight();
                if (mode1Val > mode2Val) {
                    return -1;
                } else if (mode1Val == mode2Val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        for (DisplayMode mode : _displayModes) {
            resolutionsDropDown.addItem(String.format("%dx%d@%dHZ",
                    mode.getWidth(),
                    mode.getHeight(),
                    mode.getRefreshRate()));
        }
    }
}
