package blocknix.gui;

import blocknix.Utils;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

import java.util.logging.Level;

/**
 * Created by peter on 9/20/2014.
 */
public class MainMenuController extends AbstractAppState implements ScreenController {

    private Application app_;
    private AppStateManager stateManager_;
    private Nifty nifty_;
    private Screen screen_;

    public void startGame() {
        Utils.log(MainMenuController.class, Level.INFO, "starting game");
    }

    public void buildStuff() {
        Utils.log(MainMenuController.class, Level.INFO, "building stuff");
    }

    public void setOptions() {
        Utils.log(MainMenuController.class, Level.INFO, "setting options");
    }

    public void quitGame() {
        Utils.log(MainMenuController.class, Level.INFO, "quitting game");
        app_.stop();
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty_ = nifty;
        this.screen_ = screen;
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app_ = app;
        this.stateManager_ = stateManager;
    }
}
