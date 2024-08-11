package framework.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static Logger log = LogManager.getLogger("");

    public static void infoToLogger(String info) {
        log.info(info);
    }
    public static void exeptionCatchToLogger(String info, Exception e){log.error(info, e);}
}
