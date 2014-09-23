package blocknix.app;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pkurl_000 on 09/20/2014.
 */
public class ServerMain extends SimpleApplication {

    private Server server_;

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
//        settings.setFrameRate(blocknix.Globals.DEFAULT_FPS);
//        settings.setRenderer(null);
//        settings.setAudioRenderer(null);

        // register serializers?
        // set logging

        ServerMain app = new ServerMain();
//        app.setShowSettings(false);
//        app.setPauseOnLostFocus(false);
        app.setSettings(settings);
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp() {
        try {
            server_ = Network.createServer(7117);
            server_.start();
        }
        catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, "Cannot start server: {0}", ex);
            return;
        }

        // initialize managers
    }

    @Override
    public void destroy() {
        super.destroy();
        server_.close();
    }
}
