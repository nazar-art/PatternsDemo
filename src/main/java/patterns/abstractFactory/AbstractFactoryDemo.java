package patterns.abstractFactory;

import patterns.abstractFactory.factories.IToyFactory;
import patterns.abstractFactory.factories.TeddyToyFactory;
import patterns.abstractFactory.factories.WoodenToyFactory;
import patterns.abstractFactory.toys.Bear;
import patterns.abstractFactory.toys.Cat;

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
