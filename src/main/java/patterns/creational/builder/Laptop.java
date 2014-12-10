package patterns.creational.builder;

public class Laptop {
    private String monitorResolution;
    private String processor;
    private String memory;
    private String hdd;
    private String battery;

    public String getMonitorResolution() {
        return monitorResolution;
    }

    public void setMonitorResolution(String monitorResolution) {
        this.monitorResolution = monitorResolution;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "monitorResolution='" + monitorResolution + '\'' +
                ", processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", hdd='" + hdd + '\'' +
                ", battery='" + battery + '\'' +
                '}';
    }
}
