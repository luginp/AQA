package framework.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static Logger log = LogManager.getLogger("");

    public static void info(String info) {
        log.info("     " + info);
    }

    public static void notification(String notification) {
        log.info("NOTIFICATION: " + notification);
    }

    public static void exceptionCatch(String exceptionInfo, Exception e) {
        log.error("EXCEPTION CATCH:" + exceptionInfo, e);
    }

    public static void step(int stepNumber, String step) {
        log.info((String.format("STEP: %d", stepNumber) + step));
    }

    public static void check(String check) {
        log.info(("CHECK: " + check));
    }
}
