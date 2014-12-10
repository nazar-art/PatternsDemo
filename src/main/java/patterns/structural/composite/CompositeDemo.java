package patterns.structural.composite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface IDocumentComponent {
    String gatherData();

    void addComponent(IDocumentComponent documentComponent);
}

class DocumentComponent implements IDocumentComponent {
    private String name;
    public List<IDocumentComponent> documentComponents;

    DocumentComponent(String name) {
        this.name = name;
        documentComponents = new ArrayList<IDocumentComponent>();
    }

    @Override
    public String gatherData() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<%s>\n", name));
        for (IDocumentComponent documentComponent : documentComponents) {
            documentComponent.gatherData();
            builder.append(documentComponent.gatherData());
        }
        builder.append(String.format("</%s>\n", name));
        return builder.toString();
    }

    @Override
    public void addComponent(IDocumentComponent documentComponent) {
        documentComponents.add(documentComponent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IDocumentComponent> getDocumentComponents() {
        return documentComponents;
    }

    public void setDocumentComponents(List<IDocumentComponent> documentComponents) {
        this.documentComponents = documentComponents;
    }
}

class CustomerDocumentComponent implements IDocumentComponent {
    private int customerId;

    CustomerDocumentComponent(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String gatherData() {
        String customerData = null;
        switch (customerId) {
            case 42:
                customerData = "Nazar Lelyak";
                break;
            default:
                customerData = "Someone else";
                break;
        }
        return String.format("<customer>%s</customer>\n", customerData);
    }

    @Override
    public void addComponent(IDocumentComponent documentComponent) {
        System.out.println("Can't add to leaf!");
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

class OrderDocumentComponent implements IDocumentComponent {

    private int orderId;

    OrderDocumentComponent(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String gatherData() {
        String orderData = null;
        switch (orderId) {
            case 0:
                orderData = "Kindle;Book1;Book2";
                break;
            default:
                orderData = "Phone;Kable;Headset";
                break;
        }
        return String.format("<order>%s</order>\n", orderData);
    }

    @Override
    public void addComponent(IDocumentComponent documentComponent) {
        System.out.println("can not add to leaf!");
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

class HeaderDocumentComponent implements IDocumentComponent {

    @Override
    public String gatherData() {
        return String.format("<header>\n<messageTime>%s</messageTime>\n</header>\n", new SimpleDateFormat("hh:mm:ss")
                .format(new Date()));
    }

    @Override
    public void addComponent(IDocumentComponent documentComponent) {
        System.out.println("can not add to leaf!");
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        DocumentComponent document = new DocumentComponent("document");
        DocumentComponent body = new DocumentComponent("body");

        document.addComponent(new HeaderDocumentComponent());
        document.addComponent(body);

        CustomerDocumentComponent customerComponent = new CustomerDocumentComponent(42);
        DocumentComponent orders = new DocumentComponent("orders");
        orders.addComponent(new OrderDocumentComponent(0));
        orders.addComponent(new OrderDocumentComponent(1));
        body.addComponent(customerComponent);
        body.addComponent(orders);
        System.out.println(document.gatherData());
    }
}
