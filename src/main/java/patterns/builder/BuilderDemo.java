package patterns.builder;

public class BuilderDemo {
    public static void main(String[] args) {
        Laptop laptop;
        TripLaptopBuilder tripLaptopBuilder = new TripLaptopBuilder();
        GamingLaptopBuilder gamingLaptopBuilder = new GamingLaptopBuilder();
        BuyLaptop buyLaptop = new BuyLaptop();

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
