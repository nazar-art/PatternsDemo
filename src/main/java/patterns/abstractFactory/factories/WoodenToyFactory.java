package patterns.abstractFactory.factories;

import patterns.abstractFactory.toys.Bear;
import patterns.abstractFactory.toys.Cat;
import patterns.abstractFactory.toys.WoodenBear;
import patterns.abstractFactory.toys.WoodenCat;

public class WoodenToyFactory implements IToyFactory {

    @Override
    public Bear getBear() {
        return new WoodenBear("Wooden Bear");
    }

    @Override
    public Cat getCat() {
        return new WoodenCat("Wooden Cat");
    }
}
