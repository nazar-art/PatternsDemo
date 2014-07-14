package patterns.builder;

public class BuyLaptop {
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
