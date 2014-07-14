package patterns.factoryMethod;

public interface ILogger {
    public void logMessage(String message);

    public void logError();

    public void logVerboseInformation();
}
