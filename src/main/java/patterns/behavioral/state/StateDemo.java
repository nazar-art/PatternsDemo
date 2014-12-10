package patterns.behavioral.state;

import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

abstract class OrderState {
    protected Order order;

    OrderState(Order order) {
        this.order = order;
    }

    public void addProduct() {
        unsupportedOperation("AddProduct");
    }

    public void register() {
        unsupportedOperation("Register");
    }

    public void grand() {
        unsupportedOperation("Grand");
    }

    public void ship() {
        unsupportedOperation("Ship");
    }

    public void invoice() {
        unsupportedOperation("Invoice");
    }

    public void cancel() {
        unsupportedOperation("Cancel");
    }

    protected void unsupportedOperation(String operation) {
        System.out.printf("Operation %s isn't supported for Order's state %s%n",
                operation, this.getClass().getSimpleName());
    }
}

class Order {
    private OrderState state;
    private List<Product> products = new ArrayList<>();

    Order() {
        state = new NewOrder(this);
    }

    public void currentState() {
        System.out.println("Current order's state " + state.getClass().getSimpleName());
    }

    public void addProduct(Product product) {
        products.add(product);
        state.addProduct();
    }

    public void register() {
        state.register();
    }

    public void grand() {
        state.grand();
    }

    public void ship() {
        state.ship();
    }

    public void invoice() {
        state.invoice();
    }

    public void cancel() {
        state.cancel();
    }

    public void performShipping() {
        System.out.println("Shipping...");
    }

    public void performAddProduct() {
        System.out.println("Add product...");
    }

    public void performGrand() {
        System.out.println("Grand...");
    }

    public void performRegister() {
        System.out.println("Register...");
    }

    public void performInvoice() {
        System.out.println("Invoice...");
    }

    public void performCancel() {
        System.out.println("Cancel...");
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<Product> getOrders() {
        return products;
    }

    public void setOrders(List<Product> orders) {
        this.products = products;
    }
}

class Registered extends OrderState {
    Registered(Order order) {
        super(order);
    }

    @Override
    public void addProduct() {
        order.performAddProduct();
    }

    @Override
    public void grand() {
        order.performGrand();
        order.setState(new Granted(order));
    }

    @Override
    public void cancel() {
        order.performCancel();
        order.setState(new Cancel(order));
    }
}

class Shipped extends OrderState {
    Shipped(Order order) {
        super(order);
    }

    @Override
    public void invoice() {
        order.performInvoice();
        order.setState(new Invoice(order));
    }
}

class Invoice extends OrderState {
    Invoice(Order order) {
        super(order);
    }
}

class NewOrder extends OrderState {
    NewOrder(Order order) {
        super(order);
    }

    @Override
    public void addProduct() {
        order.performAddProduct();
    }

    @Override
    public void register() {
        order.performRegister();
        order.setState(new Registered(order));
    }

    @Override
    public void cancel() {
        order.performCancel();
        order.setState(new Cancel(order));
    }
}

class Granted extends OrderState {
    Granted(Order order) {
        super(order);
    }

    @Override
    public void addProduct() {
        order.performAddProduct();
    }

    @Override
    public void ship() {
        order.performShipping();
        order.setState(new Shipped(order));
    }

    @Override
    public void cancel() {
        order.performCancel();
        order.setState(new Cancel(order));
    }
}

class Cancel extends OrderState {
    Cancel(Order order) {
        super(order);
    }
}

public class StateDemo {
    public static void main(String[] args) {
        Product pepsi = new Product();
        pepsi.setName("Pepsi Cola");
        pepsi.setPrice(18D);

        Order order = new Order();
        order.currentState();

        order.addProduct(pepsi);
        order.currentState();

        order.register();
        order.currentState();

        order.grand();
        order.currentState();

        order.ship();
        order.currentState();

        order.invoice();
        order.currentState();

        // add again
        order.addProduct(pepsi);
        order.currentState();
    }
}
