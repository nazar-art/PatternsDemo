package patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface IObserver {
    void update(ISubject subject);
}

class RiskyPlayer implements IObserver {
    private String boxerToPutMoneyOn;
    @Override
    public void update(ISubject subject) {
        BoxFight boxFight = (BoxFight) subject;
        if (boxFight.getBoxerAScore() > boxFight.getBoxerBScore()) {
            boxerToPutMoneyOn = "I put on boxer B, if he win I get more!";
        } else {
            boxerToPutMoneyOn = "I put on boxer A, if he win I get more!";
        }
        System.out.printf("RISKY_PLAYER: %s%n", boxerToPutMoneyOn);
    }

    public String getBoxerToPutMoneyOn() {
        return boxerToPutMoneyOn;
    }
}

class ConservativePlayer implements IObserver {
    private String boxerToPutMoneyOn;

    @Override
    public void update(ISubject subject) {
        BoxFight boxFight = (BoxFight) subject;
        if (boxFight.getBoxerAScore() < boxFight.getBoxerBScore()) {
            boxerToPutMoneyOn = "I put on boxer B, if he win I get more!";
        } else {
            boxerToPutMoneyOn = "I put on boxer A, if he win I get more!";
        }
        System.out.printf("CONSERVATIVE_PLAYER: %s%n", boxerToPutMoneyOn);
    }

    public String getBoxerToPutMoneyOn() {
        return boxerToPutMoneyOn;
    }
}

interface ISubject {
    void attachObserver(IObserver observer);

    void detachObserver(IObserver observer);

    void inform();
}

class BoxFight implements ISubject {
    private List<IObserver> observers;
    private int roundNumber;
    private Random random;

    private int boxerAScore;
    private int boxerBScore;

    public BoxFight() {
        observers = new ArrayList<>();
        random = new Random();
    }

    @Override
    public void attachObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void inform() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }

    public void nextRound() {
        roundNumber++;
        if (roundNumber == 1) {
            System.out.printf("Round #%d:%n", roundNumber);
        } else {
            System.out.printf("%nRound #%d:%n", roundNumber);
        }
        boxerAScore += random.nextInt(5);
        boxerBScore += random.nextInt(5);
        inform();
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    public int getBoxerAScore() {
        return boxerAScore;
    }

    public int getBoxerBScore() {
        return boxerBScore;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        BoxFight boxFight = new BoxFight();

        RiskyPlayer riskyPlayer = new RiskyPlayer();
        ConservativePlayer conservativePlayer = new ConservativePlayer();

        boxFight.attachObserver(riskyPlayer);
        boxFight.attachObserver(conservativePlayer);

        boxFight.nextRound();
        boxFight.nextRound();
        boxFight.nextRound();
        boxFight.nextRound();
    }
}
