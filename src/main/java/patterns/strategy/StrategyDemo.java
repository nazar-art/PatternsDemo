package patterns.strategy;

interface IWearingStrategy {
    String getClothes();

    String getAccessories();
}

class SunnyWeatherStrategy implements IWearingStrategy {

    @Override
    public String getClothes() {
        return "shorts, t-shirt and sandals";
    }

    @Override
    public String getAccessories() {
        return "sunglasses and panama";
    }
}

class RainWeatherStrategy implements IWearingStrategy {

    @Override
    public String getClothes() {
        return "coat and boot";
    }

    @Override
    public String getAccessories() {
        return "umbrella";
    }
}

class Myself {
    private IWearingStrategy wearingStrategy = new DefaultWearingStrategy();

    public void changeStrategy(IWearingStrategy iWearingStrategy) {
        wearingStrategy = iWearingStrategy;
    }

    public void goOutside() {
        String clothes = wearingStrategy.getClothes();
        String accessories = wearingStrategy.getAccessories();
        System.out.printf("Today I wore %s and took %s%n", clothes, accessories);
    }
}

class DefaultWearingStrategy implements IWearingStrategy {

    @Override
    public String getClothes() {
        return "jeans, skirt and sneakers";
    }

    @Override
    public String getAccessories() {
        return "buff";
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        Myself meToday = new Myself();
        meToday.changeStrategy(new RainWeatherStrategy());
        meToday.goOutside();

        meToday.changeStrategy(new SunnyWeatherStrategy());
        meToday.goOutside();
    }
}
