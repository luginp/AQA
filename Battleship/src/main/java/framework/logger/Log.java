package framework.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static Logger log = LogManager.getLogger("");

    public static void info(String info) {
        log.info("     " + info);
    }
    public static void notification(String notification)
    {
        log.info("NOTIFICATION: " + notification);
    }

    public static void exceptionCatch(String exceptionInfo, Exception e) {
        log.error("EXCEPTION CATCH:" + exceptionInfo, e);
    }

    public static void step(String step) {
        log.info(("STEP: " + step));
    }
}
