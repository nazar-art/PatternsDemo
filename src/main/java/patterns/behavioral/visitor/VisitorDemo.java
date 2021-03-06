package patterns.behavioral.visitor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

interface IVisitor {
    void visit(OfficeBuilding building);

    void visit(Floor floor);

    void visit(Room room);
}

class ElectricitySystemValidator implements IVisitor {

    @Override
    public void visit(OfficeBuilding building) {
        StateCondition electricityState = (building.getElectricitySystemId() > 1000) ? StateCondition.GOOD : StateCondition.BAD;
        System.out.printf("Main electric shield in building %s is in %s state.%n", building.getBuildingName(), electricityState);
    }

    @Override
    public void visit(Floor floor) {
        System.out.printf("Diagnostic electricity on the floor %s%n", floor.getFloorNumber());
    }

    @Override
    public void visit(Room room) {
        System.out.printf("Diagnosing electricity in room %d%n", room.getRoomNumber());
    }
}

class PlumbingSystemValidator implements IVisitor {
    @Override
    public void visit(OfficeBuilding building) {
        StateCondition buildingCondition = (building.getAge() < 30) ? StateCondition.GOOD : StateCondition.BAD;
        AgeCondition ageCondition = (building.getAge() < 30) ? AgeCondition.NEW : AgeCondition.OLD;
        System.out.printf("Plumbing state of building %s probably is in %s condition, since building is %s.%n",
                building.getBuildingName(), buildingCondition, ageCondition);
    }

    @Override
    public void visit(Floor floor) {
        System.out.printf("Diagnosing plumbing on floor %d%n", floor.getFloorNumber());
    }

    @Override
    public void visit(Room room) {
    }
}

enum StateCondition {
    BAD, GOOD
}

enum AgeCondition {
    NEW, OLD
}

interface IElement {
    void accept(IVisitor visitor);
}

@Getter
class OfficeBuilding implements IElement {
    public int age;
    private final String buildingName;
    private final int electricitySystemId;
    private final List<Floor> floors = new ArrayList<>();

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
        for (Floor floor : floors) {
            floor.accept(visitor);
        }
    }

    OfficeBuilding(String buildingName, int age, int electricitySystemId) {
        this.buildingName = buildingName;
        this.age = age;
        this.electricitySystemId = electricitySystemId;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

}

@Getter
class Floor implements IElement {

    private final int floorNumber;
    private final List<Room> rooms = new ArrayList<>();

    Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
        for (Room room : rooms) {
            room.accept(visitor);
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}

@Getter
@RequiredArgsConstructor
class Room implements IElement {

    private final int roomNumber;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

public class VisitorDemo {
    public static void main(String[] args) {
        Floor floorOne = new Floor(1);
        floorOne.addRoom(new Room(100));
        floorOne.addRoom(new Room(101));
        floorOne.addRoom(new Room(102));

        Floor floorTwo = new Floor(2);
        floorTwo.addRoom(new Room(200));
        floorTwo.addRoom(new Room(201));
        floorTwo.addRoom(new Room(202));

        OfficeBuilding office = new OfficeBuilding("New Business center", 25, 990);
        office.addFloor(floorOne);
        office.addFloor(floorTwo);

        ElectricitySystemValidator electricity = new ElectricitySystemValidator();
        office.accept(electricity);

        PlumbingSystemValidator plumbing = new PlumbingSystemValidator();
        office.accept(plumbing);
    }
}
