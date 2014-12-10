package patterns.structural.bridge;

interface IBuildingCompany {
    void buildFoundation();

    void buildRoof();

    void buildRoom();
}

interface IWallCreator {
    void buildWallWithDoor();

    void buildWallWithWindow();

    void buildWall();
}

class BuildingCompany implements IBuildingCompany {

    private IWallCreator wallCreator;

    @Override
    public void buildFoundation() {
        System.out.println("Foundation is built\n");
    }

    @Override
    public void buildRoof() {
        System.out.println("Roof is done");
    }

    @Override
    public void buildRoom() {
        wallCreator.buildWallWithDoor();
        wallCreator.buildWall();
        wallCreator.buildWallWithWindow();
        wallCreator.buildWall();
        System.out.println("Room is done\n");
    }

    public IWallCreator getWallCreator() {
        return wallCreator;
    }
    public void setWallCreator(IWallCreator wallCreator) {
        this.wallCreator = wallCreator;
    }
}

class ConcreteWallCreator implements IWallCreator {

    @Override
    public void buildWallWithDoor() {
        System.out.println("Concrete wall with door");
    }

    @Override
    public void buildWallWithWindow() {
        System.out.println("Concrete wall with window");
    }

    @Override
    public void buildWall() {
        System.out.println("Concrete wall");
    }
}

class BrickWallCreator implements IWallCreator {

    @Override
    public void buildWallWithDoor() {
        System.out.println("Brick wall with door");
    }

    @Override
    public void buildWallWithWindow() {
        System.out.println("Brick wall with window");
    }

    @Override
    public void buildWall() {
        System.out.println("Brick wall");
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        BuildingCompany buildingCompany = new BuildingCompany();

        ConcreteWallCreator concreteWallCreator = new ConcreteWallCreator();
        BrickWallCreator brickWallCreator = new BrickWallCreator();

        buildingCompany.buildFoundation();
        buildingCompany.setWallCreator(concreteWallCreator);
        buildingCompany.buildRoom();

        // switch to another wall type
        buildingCompany.setWallCreator(brickWallCreator);
        buildingCompany.buildRoom();
        buildingCompany.buildRoom();
        buildingCompany.buildRoof();
    }
}
