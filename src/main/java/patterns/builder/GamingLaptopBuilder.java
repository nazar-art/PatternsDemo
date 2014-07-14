package patterns.builder;

public class GamingLaptopBuilder extends LaptopBuilder {

    private static final String MONITOR_RESOLUTION = "1900x1200";
    private static final String PROCESSOR = "Core 2 Duo, 3.2 Ghz";
    private static final String MEMORY = "6144 Mb";
    private static final String HDD = "500 Gb";
    private static final String BATTERY = "6 lbs";

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
