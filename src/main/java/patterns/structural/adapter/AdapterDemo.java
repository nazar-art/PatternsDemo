package patterns.structural.adapter;

import org.apache.log4j.Logger;

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
    private static Logger LOG = Logger.getLogger(Adapter.class);
    private OldElectricitySystem oldElectricitySystem;

    Adapter(OldElectricitySystem oldElectricitySystem) {
        this.oldElectricitySystem = oldElectricitySystem;
    }

    @Override
    public String matchWideSocket() {
        LOG.info("adapter matches for wide socket");
        return oldElectricitySystem.matchThinSocket();
    }
}

class ElectricityCustomer {
    private static Logger LOG = Logger.getLogger(ElectricityCustomer.class);
    // this laptop only for new system for wide sockets
    public static void chargeLaptop(INewElectricitySystem electricitySystem) {
        LOG.info("laptop is charged from " + electricitySystem.matchWideSocket());
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        NewElectricitySystem newElectricitySystem = new NewElectricitySystem();
        ElectricityCustomer.chargeLaptop(newElectricitySystem);

        OldElectricitySystem oldElectricitySystem = new OldElectricitySystem();
        Adapter adapter = new Adapter(oldElectricitySystem);
        ElectricityCustomer.chargeLaptop(adapter);
    }
}
