package patterns.creational.builder;

class Laptop {
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
abstract class LaptopBuilder {

    protected Laptop laptop;

    public void createNewLaptop() {
        laptop = new Laptop();
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    //mentioned steps to build laptop
    public abstract void setMonitorResolution();

    public abstract void setProcessor();

    public abstract void setMemory();

    public abstract void setHDD();

    public abstract void setBattery();
}

class GamingLaptopBuilder extends LaptopBuilder {
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

class TripLaptopBuilder extends LaptopBuilder {
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

class BuyLaptop {

    private LaptopBuilder laptopBuilder;

    public LaptopBuilder getLaptopBuilder() {
        return laptopBuilder;
    }

    public void setLaptopBuilder(LaptopBuilder laptopBuilder) {
        this.laptopBuilder = laptopBuilder;
    }

    public Laptop getLaptop() {
        return laptopBuilder.getLaptop();
    }
    // construct additional details
    public void constructLaptop() {
        laptopBuilder.createNewLaptop();
        laptopBuilder.setMonitorResolution();
        laptopBuilder.setProcessor();
        laptopBuilder.setMemory();
        laptopBuilder.setHDD();
        laptopBuilder.setBattery();
    }

}

public class BuilderDemo {
    public static void main(String[] args) {
        Laptop laptop;
        BuyLaptop buyLaptop = new BuyLaptop();
        TripLaptopBuilder tripLaptopBuilder = new TripLaptopBuilder();
        GamingLaptopBuilder gamingLaptopBuilder = new GamingLaptopBuilder();

        buyLaptop.setLaptopBuilder(tripLaptopBuilder);
        buyLaptop.constructLaptop();
        laptop = buyLaptop.getLaptop();
        System.out.printf("Here is created laptop for trip: %s%n", laptop.toString());

        buyLaptop.setLaptopBuilder(gamingLaptopBuilder);
        buyLaptop.constructLaptop();
        laptop = buyLaptop.getLaptop();
        System.out.printf("Here is created laptop for gaming: %s%n", laptop.toString());
    }
}
