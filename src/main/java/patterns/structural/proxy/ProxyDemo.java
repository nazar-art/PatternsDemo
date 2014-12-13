package patterns.structural.proxy;

import java.util.Random;

class RobotBombDefuse {
    private Random random = new Random();
    private static final int ROBOT_WAVELENGTH = 42;
    private boolean isConnected = false;

    public void connectionWireless(int communicationWavelength) {
        if (communicationWavelength == ROBOT_WAVELENGTH) {
            isConnected = imitationConnection();
        }
    }

    private boolean imitationConnection() {
        return random.nextInt(10) < 5; // imitation 50% for success connection
    }

    public boolean isConnected() {
        isConnected = imitationConnection();
        return isConnected;
    }

    public void walkForward(int steps) {
        System.out.printf("Did %d steps forward... ", steps);
    }

    public void turnRight() {
        System.out.printf("Turn right.... ");
    }

    public void turnLeft() {
        System.out.printf("Turn right... ");
    }

    public void defuseBomb() {
        System.out.println("Cut red or green of blue wire...");
    }
}

class RobotBombDefuseProxy extends RobotBombDefuse {
    private RobotBombDefuse robotBombDefuse;
    private int communicationWavelength;
    private static final int CONNECTION_ATTEMPTS = 3;

    RobotBombDefuseProxy(int communication) {
        robotBombDefuse = new RobotBombDefuse();
        this.communicationWavelength = communication;
    }

    @Override
    public void walkForward(int steps) {
        ensureRobotConnection();
        super.walkForward(steps);
    }

    @Override
    public void turnRight() {
        ensureRobotConnection();
        super.turnRight();
    }

    @Override
    public void turnLeft() {
        ensureRobotConnection();
        super.turnLeft();
    }

    @Override
    public void defuseBomb() {
        ensureRobotConnection();
        super.defuseBomb();
    }

    private void ensureRobotConnection() {
        if (robotBombDefuse == null) {
            robotBombDefuse = new RobotBombDefuse();
            robotBombDefuse.connectionWireless(communicationWavelength);
        }

        for (int i = 0; i < CONNECTION_ATTEMPTS; i++) {
            if (!robotBombDefuse.isConnected()) {
                robotBombDefuse.connectionWireless(communicationWavelength);
            } else {
                break;
            }
        }
        if (!robotBombDefuse.isConnected()) {
            throw new BadConnectionException("No connection with defuse robot, after few attempts");
        }
    }
}

class BadConnectionException extends RuntimeException {
    BadConnectionException(String message) {
        super(message);
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        int num = 0;
        try {
            RobotBombDefuseProxy proxy = new RobotBombDefuseProxy(42);
            proxy.walkForward(100);
            num++;
            proxy.turnRight();
            num++;
            proxy.walkForward(5);
            num++;
            proxy.defuseBomb();
        } catch (BadConnectionException e) {
            System.out.printf("\nFu..! Huston we on trouble! Exception occurred - %s%nTake human control under bomb defuse robot:%n", e.getMessage());
            planB(num);
        }
    }

    private static void planB(int progressNumber) {
        System.out.println("Human control under robot started:");
        RobotBombDefuse humanControlRobot = new RobotBombDefuse();
        switch (progressNumber) {
            case 0:
                humanControlRobot.walkForward(100);
                System.out.println();
            case 1:
                humanControlRobot.turnRight();
                System.out.println();
            case 2:
                humanControlRobot.walkForward(5);
                System.out.println();
            case 3:
                humanControlRobot.defuseBomb();
                break;
            default:
                System.out.printf("Wrong number of operation - %d stop bomb defuse!!%n", progressNumber);
                System.exit(1);
        }
    }
}
