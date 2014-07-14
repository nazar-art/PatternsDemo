package patterns.abstractFactory.factories;

import patterns.abstractFactory.toys.Bear;
import patterns.abstractFactory.toys.Cat;
import patterns.abstractFactory.toys.TeddyBear;
import patterns.abstractFactory.toys.TeddyCat;

public class TeddyToyFactory implements IToyFactory {
    @Override
    public Bear getBear() {
        return new TeddyBear("Teddy Bear");
    }

    @Override
    public Cat getCat() {
        return new TeddyCat("Teddy Cat");
    }
}
