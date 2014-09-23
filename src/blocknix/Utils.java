package blocknix;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pkurl_000 on 09/22/2014.
 */
public class Utils {
    public static void log(Class<?> cls, Level level, String message, Object... args) {
        Logger.getLogger(cls.getName()).log(level, String.format(message, args));
    }
}
