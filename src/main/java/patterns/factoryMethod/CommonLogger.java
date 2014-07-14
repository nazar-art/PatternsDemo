package patterns.factoryMethod;

public class CommonLogger implements ILogger {
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
