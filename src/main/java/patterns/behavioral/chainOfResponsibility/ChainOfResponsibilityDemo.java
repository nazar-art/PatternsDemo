package patterns.behavioral.chainOfResponsibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class WeirdCafeVisitor {
    public WeirdCafeVisitor cafeVisitor;

    protected WeirdCafeVisitor(WeirdCafeVisitor visitor) {
        cafeVisitor = visitor;
    }

    protected void handleFood(Food food) {
        if (cafeVisitor != null) {
            cafeVisitor.handleFood(food);
        }
    }
}

class Food {
    private String name;
    private List<String> ingredients;

    public Food(String name, List<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}

class GirlFriend extends WeirdCafeVisitor {

    protected GirlFriend(WeirdCafeVisitor visitor) {
        super(visitor);
    }

    @Override
    protected void handleFood(Food food) {
        if (food.getName().equalsIgnoreCase("Cappuccino")) {
            System.out.println("GirlFriend: My lovely Cappuccino!!!");
            return;
        }
        super.handleFood(food);
    }
}

class Me extends WeirdCafeVisitor {
    protected Me(WeirdCafeVisitor visitor) {
        super(visitor);
    }

    @Override
    protected void handleFood(Food food) {
        if (food.getName().contains("Soup")) {
            System.out.println("Me: I like Soup. It went well.");
        }
        super.handleFood(food);
    }
}

class BestFriend extends WeirdCafeVisitor {
    private List<Food> coffeeContainingFood;

    protected BestFriend(WeirdCafeVisitor visitor) {
        super(visitor);
        coffeeContainingFood = new ArrayList<>();
    }

    @Override
    protected void handleFood(Food food) {
        if (food.getIngredients().contains("Meat")) {
            System.out.printf("BestFriend: I just ate %s. It was testy.%n", food.getName());
            return;
        } else if (food.getIngredients().contains("Coffee") && coffeeContainingFood.size() < 1) {
            coffeeContainingFood.add(food);
            System.out.printf("BestFriend: I have to take something with coffee. %s looks fine.%n", food.getName());
            return;
        }
        super.handleFood(food);
    }

    public List<Food> getCoffeeContainingFood() {
        return coffeeContainingFood;
    }

    public void setCoffeeContainingFood(List<Food> coffeeContainingFood) {
        this.coffeeContainingFood = coffeeContainingFood;
    }
}

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Food cappuccino1 = new Food("Cappuccino", Arrays.asList("Coffee", "Milk", "Sugar"));
        Food cappuccino2 = new Food("Cappuccino", Arrays.asList("Coffee", "Milk"));
        Food soup1 = new Food("Soup with meat", Arrays.asList("Meat", "Water", "Potato"));
        Food soup2 = new Food("Soup with meat", Arrays.asList("Water", "Potato"));
        Food meat = new Food("Meat", Arrays.asList("Meat"));

        GirlFriend girlFriend = new GirlFriend(null);
        Me me = new Me(girlFriend);
        BestFriend bestFriend = new BestFriend(me);

        bestFriend.handleFood(cappuccino1);
        bestFriend.handleFood(cappuccino2);
        bestFriend.handleFood(soup1);
        bestFriend.handleFood(soup2);
        bestFriend.handleFood(soup2);
        bestFriend.handleFood(meat);
    }
}
