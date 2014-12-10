package patterns.creational.builder;

public abstract class LaptopBuilder {
    protected Laptop laptop;

    public void createNewLaptop() {
        laptop = new Laptop();
    }

    public Laptop getMyLaptop() {
        return laptop;
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
