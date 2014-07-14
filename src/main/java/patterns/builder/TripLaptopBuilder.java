package patterns.builder;

public class TripLaptopBuilder extends LaptopBuilder {

    private static final String MONITOR_RESOLUTION = "1200x800";
    private static final String PROCESSOR = "Celeron 2 Gb";
    private static final String MEMORY = "2048 Mb";
    private static final String HDD = "360 Gb";
    private static final String BATTERY = "12 lbs";

    @Override
    public void setMonitorResolution() {
        laptop.setMonitorResolution(MONITOR_RESOLUTION);
    }

    @Override
    public void setProcessor() {
        laptop.setProcessor(PROCESSOR);
    }

    @Override
    public void setMemory() {
        laptop.setMemory(MEMORY);
    }

    @Override
    public void setHDD() {
        laptop.setHdd(HDD);
    }

    @Override
    public void setBattery() {
        laptop.setBattery(BATTERY);
    }
}
