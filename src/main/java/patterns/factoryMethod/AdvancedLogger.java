package patterns.factoryMethod;

public class AdvancedLogger implements ILogger {
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
