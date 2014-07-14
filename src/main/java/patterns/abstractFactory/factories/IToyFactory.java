package patterns.abstractFactory.factories;

import patterns.abstractFactory.toys.Bear;
import patterns.abstractFactory.toys.Cat;

public interface IToyFactory {
    public Bear getBear();

    public Cat getCat();
}
