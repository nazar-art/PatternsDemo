package patterns.creational.prototype;

abstract class Prototype implements Cloneable {
    @Override
    protected abstract Object clone() throws CloneNotSupportedException;
}

abstract class BattleDroid extends Prototype {
    @Override
    protected abstract BattleDroid clone() throws CloneNotSupportedException;
}

abstract class RepairDroid extends Prototype {
    @Override
    protected abstract RepairDroid clone() throws CloneNotSupportedException;
}

abstract class SuperBattleDroid extends Prototype {
    @Override
    protected abstract SuperBattleDroid clone() throws CloneNotSupportedException;
}

interface IDroidFactory {
    BattleDroid createBattleDroid();

    RepairDroid createRepairDroid();

    SuperBattleDroid createSuperDroid();
}

class DroidFactoryImpl implements IDroidFactory {
    private BattleDroid battleDroid;
    private RepairDroid repairDroid;
    private SuperBattleDroid superBattleDroid;

    public DroidFactoryImpl(BattleDroid battleDroid, RepairDroid repairDroid, SuperBattleDroid superBattleDroid) {
        this.battleDroid = battleDroid;
        this.repairDroid = repairDroid;
        this.superBattleDroid = superBattleDroid;
    }

    @Override
    public BattleDroid createBattleDroid() {
        try {
            return battleDroid.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RepairDroid createRepairDroid() {
        try {
            return repairDroid.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SuperBattleDroid createSuperDroid() {
        try {
            return superBattleDroid.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class GoodDroid extends BattleDroid {
    public GoodDroid() {
    }

    public GoodDroid(GoodDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected BattleDroid clone() throws CloneNotSupportedException {
        return new GoodDroid(this);
    }

    @Override
    public String toString() {
        return "GoodDroid{}";
    }
}

class GoodRepairDroid extends RepairDroid {
    public GoodRepairDroid() {
    }

    public GoodRepairDroid(GoodRepairDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected RepairDroid clone() throws CloneNotSupportedException {
        return new GoodRepairDroid(this);
    }

    @Override
    public String toString() {
        return "GoodRepairDroid{}";
    }
}

class GoodSuperBattleDroid extends SuperBattleDroid {
    public GoodSuperBattleDroid() {
    }

    public GoodSuperBattleDroid(GoodSuperBattleDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected SuperBattleDroid clone() throws CloneNotSupportedException {
        return new GoodSuperBattleDroid(this);
    }

    @Override
    public String toString() {
        return "GoodSuperBattleDroid{}";
    }
}

class EvilDroid extends BattleDroid {
    public EvilDroid() {
    }

    public EvilDroid(EvilDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected BattleDroid clone() throws CloneNotSupportedException {
        return new EvilDroid(this);
    }

    @Override
    public String toString() {
        return "EvilDroid{}";
    }
}

class EvilRepairDroid extends RepairDroid {
    public EvilRepairDroid() {
    }

    public EvilRepairDroid(EvilRepairDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected RepairDroid clone() throws CloneNotSupportedException {
        return new EvilRepairDroid(this);
    }

    @Override
    public String toString() {
        return "EvilRepairDroid{}";
    }
}

class EvilSuperDroid extends SuperBattleDroid {
    public EvilSuperDroid() {
    }

    public EvilSuperDroid(EvilSuperDroid droid) {
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    protected SuperBattleDroid clone() throws CloneNotSupportedException {
        return new EvilSuperDroid(this);
    }

    @Override
    public String toString() {
        return "EvilSuperDroid{}";
    }
}

public class PrototypeDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        IDroidFactory factory;
        BattleDroid battleDroid;
        RepairDroid repairDroid;
        SuperBattleDroid superBattleDroid;

        factory = new DroidFactoryImpl(new GoodDroid(), new GoodRepairDroid(), new GoodSuperBattleDroid());
        battleDroid = factory.createBattleDroid();
        repairDroid = factory.createRepairDroid();
        superBattleDroid = factory.createSuperDroid();
        System.out.printf("Initialise instances:\n %s\n %s\n %s%n", battleDroid, repairDroid, superBattleDroid);

        factory = new DroidFactoryImpl(new EvilDroid(), new EvilRepairDroid(), new EvilSuperDroid());
        battleDroid = factory.createBattleDroid();
        repairDroid = factory.createRepairDroid();
        superBattleDroid = factory.createSuperDroid();
        System.out.printf("Use prototype instances:\n %s\n %s\n %s%n", battleDroid, repairDroid, superBattleDroid);
    }
}
