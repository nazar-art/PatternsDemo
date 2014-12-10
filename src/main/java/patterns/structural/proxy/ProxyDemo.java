package patterns.structural.proxy;

import java.util.Random;

class RobotBombDefuser {
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

class RobotBombDefuserProxy extends RobotBombDefuser {
    private RobotBombDefuser robotBombDefuser;
    private int communicationWavelengt;
    private static final int CONNECTION_ATTEMPTS = 3;

    RobotBombDefuserProxy(int communication) {
        robotBombDefuser = new RobotBombDefuser();
        this.communicationWavelengt = communication;
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
        if (robotBombDefuser == null) {
            robotBombDefuser = new RobotBombDefuser();
            robotBombDefuser.connectionWireless(communicationWavelengt);
        }

        for (int i = 0; i < CONNECTION_ATTEMPTS; i++) {
            if (!robotBombDefuser.isConnected()) {
                robotBombDefuser.connectionWireless(communicationWavelengt);
            } else {
                break;
            }
        }
        if (!robotBombDefuser.isConnected()) {
            throw new BadConnectionException("No connection with defuser robot, after few attempts");
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
            RobotBombDefuserProxy proxy = new RobotBombDefuserProxy(42);
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
        RobotBombDefuser humanControlRobot = new RobotBombDefuser();
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
                System.out.println("Wrong number of operation - " + progressNumber + " stop bomb defuse!!");
                System.exit(1);
        }
    }
}
