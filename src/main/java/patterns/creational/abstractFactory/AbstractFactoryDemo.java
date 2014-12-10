package patterns.creational.abstractFactory;

interface IToyFactory {
    public Bear getBear();

    public Cat getCat();
}

class TeddyToyFactory implements IToyFactory {
    @Override
    public Bear getBear() {
        return new TeddyBear("Teddy Bear");
    }

    @Override
    public Cat getCat() {
        return new TeddyCat("Teddy Cat");
    }
}

class WoodenToyFactory implements IToyFactory {

    @Override
    public Bear getBear() {
        return new WoodenBear("Wooden Bear");
    }

    @Override
    public Cat getCat() {
        return new WoodenCat("Wooden Cat");
    }
}

abstract class AnimalToy {
    private String name;

    protected AnimalToy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

abstract class Bear extends AnimalToy {
    public Bear(String name) {
        super(name);
    }
}

abstract class Cat extends AnimalToy {
    public Cat(String name) {
        super(name);
    }
}

class TeddyBear extends Bear {
    public TeddyBear(String name) {
        super(name);
    }
}

class TeddyCat extends Cat {
    public TeddyCat(String name) {
        super(name);
    }
}

class WoodenBear extends Bear {
    public WoodenBear(String name) {
        super(name);
    }
}

class WoodenCat extends Cat {
    public WoodenCat(String name) {
        super(name);
    }
}

public class AbstractFactoryDemo {
    private void testWoodenFactory() {
        IToyFactory toyFactory = new WoodenToyFactory();
        Cat cat = toyFactory.getCat();
        Bear bear = toyFactory.getBear();
        System.out.printf("You have got %s and %s%n", cat.getName(), bear.getName());
    }

    private void testTeddyFactory() {
        IToyFactory toyFactory = new TeddyToyFactory();
        Cat cat = toyFactory.getCat();
        Bear bear = toyFactory.getBear();
        System.out.printf("You have got %s and %s%n", cat.getName(), bear.getName());
    }

    public static void main(String[] args) {
        AbstractFactoryDemo factoryDemo = new AbstractFactoryDemo();
        factoryDemo.testTeddyFactory();
        factoryDemo.testWoodenFactory();
    }
}
