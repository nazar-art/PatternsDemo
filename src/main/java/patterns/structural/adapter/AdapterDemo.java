package patterns.structural.adapter;

class OldElectricitySystem {
    public String matchThinSocket() {
        return "220V from thin socket";
    }
}

interface INewElectricitySystem {
    String matchWideSocket();
}

class NewElectricitySystem implements INewElectricitySystem {

    @Override
    public String matchWideSocket() {
        return "220V from wide socket";
    }
}

class Adapter implements INewElectricitySystem {

    private OldElectricitySystem adaptee;

    Adapter(OldElectricitySystem adaptee) {
//        System.out.println("adapter created");
        this.adaptee = adaptee;
    }

    @Override
    public String matchWideSocket() {
        System.out.print("adapter matches for wide socket ");
        return adaptee.matchThinSocket();
    }
}

class ElectricityCustomer {
    // this laptop only for new system for wide sockets
    public static void chargeLaptop(INewElectricitySystem electricitySystem) {
        System.out.println("laptop is charged from " + electricitySystem.matchWideSocket());
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        NewElectricitySystem newElectricitySystem = new NewElectricitySystem();
        ElectricityCustomer.chargeLaptop(newElectricitySystem);

        OldElectricitySystem oldElectricitySystem = new OldElectricitySystem();
        ElectricityCustomer.chargeLaptop(new Adapter(oldElectricitySystem));
    }
}
