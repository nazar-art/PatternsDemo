package patterns.structural.decorator;

import org.apache.log4j.Logger;

import java.awt.*;

class BasicCoffee {
    private static Logger log = Logger.getLogger(BasicCoffee.class);
    private String type;

    public BasicCoffee() {
//        log.info("BasicCoffee constructor is called");
    }

    public BasicCoffee(String type) {
        setType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class CoffeeDecorator extends BasicCoffee {
    protected BasicCoffee basicCoffee;

    public CoffeeDecorator(BasicCoffee coffee) {
        basicCoffee = coffee;
    }

    public void setType(String type) {
        basicCoffee.setType(type);
    }

    public String getType() {
        return basicCoffee.getType();
    }

}

class SteamedMilk extends CoffeeDecorator {

    public SteamedMilk(BasicCoffee coffee) {
        super(coffee);
        setType(getType() + " & steamed milk ");
    }
}

class Foam extends CoffeeDecorator {

    public Foam(BasicCoffee coffee) {
        super(coffee);
        setType(getType() + "& foam ");
    }
}

class Chocolate extends CoffeeDecorator {
    private final Color color;

    public Chocolate(BasicCoffee coffee, Color color) {
        super(coffee);
        this.color = color;
        setType(getType() + " & chocolate[color = " + getColor() + "]");
    }

    public Color getColor() {
        return color;
    }
}

class Caramel extends CoffeeDecorator {

    public Caramel(BasicCoffee coffee) {
        super(coffee);
        setType(getType() + " & caramel");
    }
}

class WhippedCream extends CoffeeDecorator {

    public WhippedCream(BasicCoffee coffee) {
        super(coffee);
        setType(getType() + " & whipped cream");
    }
}

public class CoffeeDecoratorDemo {
    public static void main(String[] args) {
        CoffeeDecorator cappuccino = new Foam(new SteamedMilk(new Caramel(new BasicCoffee("espresso"))));
        System.out.printf("Cappuccino is: %s%n", cappuccino.getType());

        CoffeeDecorator whiteChocolateCoffee = new WhippedCream(new Chocolate(new BasicCoffee("hot coffee"), Color.WHITE));
        System.out.printf("White Chocolate Coffee is: %s%n", whiteChocolateCoffee.getType());
    }
}
