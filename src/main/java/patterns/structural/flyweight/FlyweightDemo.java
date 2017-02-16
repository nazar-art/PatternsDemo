package patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Image {
    private List<String> imitatesHugeImages = new ArrayList<>();

    public static Image load(String name) {
        Image image = new Image();
        for (int i = 0; i < 10000; i++) {
            image.imitatesHugeImages.add(String.format("abcdefg: %d", i));
        }
        return image;
    }
}

abstract class Unit {
    protected String name;
    protected int health;
    protected Image avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}

class Goblin extends Unit {
    public Goblin(boolean isFlyweight) {
        name = "Goblin";
        health = 8;
        if (isFlyweight) {
            // with Flyweight
            avatar = UnitImageFactory.createGoblinImage();
        } else {
            // without Flyweight
            avatar = Image.load("Goblin.jpg");
        }
    }
}

class Dragon extends Unit {
    public Dragon(boolean isFlyweight) {
        name = "Dragon";
        health = 50;
        if (isFlyweight) {
            // with Flyweight
            avatar = UnitImageFactory.createDragonImage();
        } else {
            // without Flyweight
            avatar = Image.load("Dragon.jpg");
        }
    }
}

class UnitImageFactory {
    public static final String DRAGON_KEY = "Dragon";
    public static final String GOBLIN_KEY = "Goblin";
    public static HashMap<String, Image> images = new HashMap<>();

    public static Image createDragonImage() {
        if (!images.containsKey(DRAGON_KEY)) {
            images.put(DRAGON_KEY, Image.load("Dragon.jpg"));
        }
        return images.get(DRAGON_KEY);
    }

    public static Image createGoblinImage() {
        if (!images.containsKey(GOBLIN_KEY)) {
            images.put(GOBLIN_KEY, Image.load("Goblin.jpg"));
        }
        return images.get(GOBLIN_KEY);
    }
}

class Parser {
    public List<Unit> parseHtml(boolean isFlyweight) {
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            units.add(new Dragon(isFlyweight));
        }
        for (int i = 0; i < 500; i++) {
            units.add(new Goblin(isFlyweight));
        }
        System.out.println("Imitations of parsing Dragons and Goblins from HTML page");
        return units;
    }
}

public class FlyweightDemo {
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) {
        System.out.println("Without Flyweight:");
        new Parser().parseHtml(false);
        logMemoryUsage();

        System.out.println("With Flyweight:");
        new Parser().parseHtml(true);
        logMemoryUsage();
    }

    private static void logMemoryUsage() {
        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.printf("Used memory is megabytes: %d.4\n", bytesToMegabytes(memory));
    }
}

/*Output:
Without Flyweight:
        Imitations of parsing Dragons and Goblins from HTML page
        Used memory is bytes: 324528
        Used memory is megabytes: 0.4

With flyweight:
        Imitations of parsing Dragons and Goblins from HTML page
        Used memory is bytes: 1874256
        Used memory is megabytes: 1.4*/
