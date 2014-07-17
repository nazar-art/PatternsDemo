package patterns.factoryMethod;

interface ILogger {
    public void logMessage(String message);

    public void logError();

    public void logVerboseInformation();
}

class AdvancedLogger implements ILogger {
    @Override
    public void logMessage(String message) {
        System.out.printf("Advanced Logger: %s%n", message);
    }

    @Override
    public void logError() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void logVerboseInformation() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

class CommonLogger implements ILogger {
    @Override
    public void logMessage(String message) {
        System.out.println("Common Logger: " + message);
    }

    @Override
    public void logError() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void logVerboseInformation() {
        throw new UnsupportedOperationException("Not implemented");
    }
}

class LoggerProviderFactory {
    public static ILogger getLoggerProvider(LoggingProviders loggingProviders) {
        switch (loggingProviders) {
            case CommonLogger:
                return new CommonLogger();
            case AdvancedLogger:
                return new AdvancedLogger();
            default:
                return new CommonLogger();
        }
    }
}

enum LoggingProviders {
    CommonLogger,
    AdvancedLogger
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        ILogger logger = LoggerProviderFactory
                .getLoggerProvider(LoggingProviders.AdvancedLogger);
        logger.logMessage("Hello Factory Method");

        logger = LoggerProviderFactory
                .getLoggerProvider(LoggingProviders.CommonLogger);
        logger.logMessage("Hello again");
    }
}
