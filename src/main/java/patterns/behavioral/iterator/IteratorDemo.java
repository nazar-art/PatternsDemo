package patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

class Soldier {
    private String name;
    private int health = 0;
    public static final int SOLDIER_HEATH_POINT = 100;

    public Soldier(String name) {
        this.name = name;
    }

    protected int maxHealthPoint() {
        return SOLDIER_HEATH_POINT;
    }

    public void treat() {
        health = SOLDIER_HEATH_POINT;
        System.out.println(name);
    }

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
}

class Hero extends Soldier {
    public static final int HERO_HEALTH_POINT = 500;

    public Hero(String name) {
        super(name);
    }

    @Override
    protected int maxHealthPoint() {
        return HERO_HEALTH_POINT;
    }
}

class Army {
    private Hero armyHero;
    private List<Group> armyGroups;

    public Army(Hero armyHero) {
        this.armyHero = armyHero;
        armyGroups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        armyGroups.add(group);
    }

    public Hero getArmyHero() {
        return armyHero;
    }

    public List<Group> getArmyGroups() {
        return armyGroups;
    }
}

class Group {
    private List<Soldier> soldiers;

    public Group() {
        soldiers = new ArrayList<>();
    }

    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }
}

class ArmyIterator {
    private Army army;
    private boolean isHeroIterated;
    private int currentGroup;
    private int currentSoldierGroup;

    public ArmyIterator(Army army) {
        this.army = army;
        isHeroIterated = false;
        currentGroup = 0;
        currentSoldierGroup = 0;
    }

    public boolean hasNext() {
        if (!isHeroIterated) return true;
        if (currentGroup < army.getArmyGroups().size()) return true;
        if (currentGroup == army.getArmyGroups().size() - 1) {
            if (currentSoldierGroup < army.getArmyGroups().get(currentGroup).getSoldiers().size()) return true;
        }
        return false;
    }

    public Soldier next() {
        Soldier nextSoldier = null;
        // we still not iterated all soldiers in current group
        if (currentGroup < army.getArmyGroups().size()) {
            if (currentSoldierGroup < army.getArmyGroups().get(currentGroup).getSoldiers().size()) {
                nextSoldier = army.getArmyGroups().get(currentGroup).getSoldiers().get(currentSoldierGroup);
                currentSoldierGroup++;
            } else {  // moving to next group
                currentGroup++;
                currentSoldierGroup = 0;
                return next();
            }
        } else if (!isHeroIterated) {  // hero is the last who left the battlefield
            isHeroIterated = true;
            return army.getArmyHero();
        } else {
            throw new RuntimeException("end of collection");
        }
        return nextSoldier;
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        Hero hero = new Hero("Hero: Nazar Lelyak");
        Army earthArmy = new Army(hero);

        Group groupA = new Group();
        fillArmyGroup(groupA, "Alpha:", 7);
        Group groupB = new Group();
        fillArmyGroup(groupB, "Betta:", 5);
        Group groupC = new Group();
        fillArmyGroup(groupC, "Gamma:", 4);

        earthArmy.addGroup(groupA);
        earthArmy.addGroup(groupB);
        earthArmy.addGroup(groupC);

        ArmyIterator armyIterator = new ArmyIterator(earthArmy);
        while (armyIterator.hasNext()) {
            armyIterator.next().treat();
        }
    }

    private static void fillArmyGroup(Group group, String groupName, int count) {
        for (int i = 1; i <= count; i++) {
            group.addSoldier(new Soldier(groupName+i));
        }
    }
}
