package patterns.behavioral.interpreter;

import java.util.*;

enum GoodsInstances {
    BED("Bad"), TV("TV"), LAPTOP("Laptop");

    private String goodName;

    GoodsInstances(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodName() {
        return goodName;
    }

    @Override
    public String toString() {
        return "GoodsInstances{" +
                "goodName='" + goodName + '\'' +
                '}';
    }

    public GoodsInstances fromString(String goodName) {
        for (GoodsInstances goodsInstance : GoodsInstances.values()) {
            if (goodsInstance.getGoodName().equalsIgnoreCase(goodName)) {
                return goodsInstance;
            } else {
                throw new IllegalArgumentException("Invalid String value: " + goodName);
            }
        }
        return null;
    }
}

class CurrentPricesContext {
    @SuppressWarnings("unchecked")
    private EnumMap<GoodsInstances, Integer> prices = new EnumMap(GoodsInstances.class);

    public CurrentPricesContext() {
        prices.put(GoodsInstances.BED, 3000);
        prices.put(GoodsInstances.TV, 400);
        prices.put(GoodsInstances.LAPTOP, 1500);
    }

    public Integer getPrice(GoodsInstances goodName) {
        if (prices.containsKey(goodName)) {
            return prices.get(goodName);
        } else {
            throw new IllegalArgumentException("Could not get price for the good that is not registered.");
        }
    }

    public void setPrice(GoodsInstances goodName, int cost) {
        if (prices.containsKey(goodName)) {
            prices.remove(goodName);
            prices.put(goodName, cost);
        } else {
            prices.put(goodName, cost);
        }
    }
}

abstract class Goods {
    public abstract Integer interpret(CurrentPricesContext context);
}

class GoodsPackage extends Goods {
    public List<? super Goods> goodsInside = new ArrayList<>();

    public GoodsPackage() {
    }

    public GoodsPackage(List<? super Goods> goodsInside) {
        this.goodsInside = goodsInside;
    }

    @Override
    public Integer interpret(CurrentPricesContext context) {
        int totalSum = 0;
        for (Iterator<? super Goods> iter = goodsInside.iterator(); iter.hasNext();) {
            Goods good = (Goods) iter.next();
            totalSum += good.interpret(context);
        }
        /*for (Goods goods : goodsInside) {
            totalSum += goods.interpret(context);
        }*/
        return totalSum;
    }

    public List<? super Goods> getGoodsInside() {
        return goodsInside;
    }

    public void setGoodsInside(List<? super Goods> goodsInside) {
        this.goodsInside = goodsInside;
    }
}

class TV extends Goods {
    @Override
    public Integer interpret(CurrentPricesContext context) {
        int price = context.getPrice(GoodsInstances.TV);
        System.out.printf("TV: %d%n", price);
        return price;
    }
}

class Bed extends Goods {
    @Override
    public Integer interpret(CurrentPricesContext context) {
        int price = context.getPrice(GoodsInstances.BED);
        System.out.printf("Bad: %d%n", price);
        return price;
    }
}

class Laptop extends Goods {
    @Override
    public Integer interpret(CurrentPricesContext context) {
        int price = context.getPrice(GoodsInstances.LAPTOP);
        System.out.printf("Laptop: %d%n", price);
        return price;
    }
}

public class InterpreterDemo {
    public static void main(String[] args) {
        new InterpreterDemo().runInterpreterDemo();
    }

    private void runInterpreterDemo() {
        GoodsPackage truckWithGoods = prepareTrackWithGoods();
        CurrentPricesContext pricesContext = getRecentPricesContext();
        Integer totalPriceForGoods = truckWithGoods.interpret(pricesContext);
        System.out.printf("Total: %d%n", totalPriceForGoods);
    }

    private CurrentPricesContext getRecentPricesContext() {
        CurrentPricesContext pricesContext = new CurrentPricesContext();
        pricesContext.setPrice(GoodsInstances.BED, 400);
        pricesContext.setPrice(GoodsInstances.TV, 100);
        pricesContext.setPrice(GoodsInstances.LAPTOP, 500);
        return pricesContext;
    }

    private GoodsPackage prepareTrackWithGoods() {
        GoodsPackage truck = new GoodsPackage();

        truck.goodsInside.add(new Bed());
        Bed bed = new Bed();
        List<Bed> goodsList = Arrays.asList(bed);
//        GoodsPackage doubleTriplePackedBed = new GoodsPackage(goodsList);
//        doubleTriplePackedBed.setGoodsInside(Arrays.asList(new GoodsPackage(Arrays.asList(bed))));
//        truck.goodsInside.add(Arrays.asList(doubleTriplePackedBed));
        truck.goodsInside.add(new TV());
        truck.goodsInside.add(new TV());
        truck.goodsInside.add(new TV());
        truck.goodsInside.add(new Laptop());
        truck.goodsInside.add(new Laptop());
        return truck;
    }
}
