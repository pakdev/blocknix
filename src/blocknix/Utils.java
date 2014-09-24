package blocknix;

import de.lessvoid.nifty.controls.DropDown;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Peter Kurlak on 09/22/2014.
 */
public class Utils {
    public static void log(Class<?> cls, Level level, String message, Object... args) {
        Logger.getLogger(cls.getName()).log(level, String.format(message, args));
    }

    public static void logInfo(Class<?> cls, String message, Object... args) {
        Utils.log(cls, Level.INFO, message, args);
    }

    public static void logSevere(Class<?> cls, String message, Object... args) {
        Utils.log(cls, Level.SEVERE, message, args);
    }
}
