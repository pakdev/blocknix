package blocknix.gui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * Created by pkurl_000 on 09/22/2014.
 */
public class OptionsController extends AbstractAppState implements ScreenController {

    private Nifty _nifty;
    private Screen _screen;
    private Application _app;
    private AppStateManager _stateManager;


    @Override
    public void bind(Nifty nifty, Screen screen) {
        _nifty = nifty;
        _screen = screen;
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
}
