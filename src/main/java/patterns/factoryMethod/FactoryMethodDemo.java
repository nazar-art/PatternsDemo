package patterns.factoryMethod;

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
