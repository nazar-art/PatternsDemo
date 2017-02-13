package patterns.creational.singleton;

import org.apache.log4j.Logger;

class LoggerSingleton {
    private final static Logger Log = Logger.getLogger(LoggerSingleton.class);
    private final static LoggerSingleton ourInstance = new LoggerSingleton();
    private int logCount = 0;

    public static LoggerSingleton getInstance() {
        return ourInstance;
    }

    private LoggerSingleton() {
    }

    public void log(String msg) {
        Log.info(String.format("%d: %s", logCount, msg));
        logCount++;
    }
}

class HardProcessor {

    private int start;

    public HardProcessor(int start) {
        this.start = start;
        LoggerSingleton.getInstance().log("Processor just created");
    }

    public int processTo(int end) {
        int sum = 0;
        for (int i = 0; i <= end; i++) {
            sum += i;
        }
        LoggerSingleton.getInstance().log("Processor just calculated some value: " + sum);
        return sum;
    }
}

public class SingletonDemo {

    public static void main(String[] args) {
        doHardWork();
    }

    private static void doHardWork() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        HardProcessor processor = new HardProcessor(1);
        logger.log("Hard work started...");
        processor.processTo(5);
        logger.log("Hard work finished.");
        logger.log("Ura!");
    }
}
