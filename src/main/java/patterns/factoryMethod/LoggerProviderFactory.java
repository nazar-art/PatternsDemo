package patterns.factoryMethod;

public class LoggerProviderFactory {
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
