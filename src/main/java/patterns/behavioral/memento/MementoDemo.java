package patterns.behavioral.memento;

import java.util.Stack;

class GameState {
    private int health;
    private int killedMonsters;

    public GameState(int health, int killedMonsters) {
        this.health = health;
        this.killedMonsters = killedMonsters;
    }

    public int getHealth() {
        return health;
    }

    public int getKilledMonsters() {
        return killedMonsters;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setKilledMonsters(int killedMonsters) {
        this.killedMonsters = killedMonsters;
    }

    @Override
    public String toString() {
        return  String.format("Health: %1$12d\nKilled Monsters: %2$3d", health, killedMonsters);
    }
}

class GameMemento {
    private GameState gameState;

    public GameMemento(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}

class GameOriginator {
    private GameState gameState = new GameState(100, 0);

    public void play() {
        System.out.println(gameState.toString());
        gameState.setHealth((int) (gameState.getHealth() * 0.9));
        gameState.setKilledMonsters(gameState.getKilledMonsters() + 2);
    }

    public GameMemento saveGame() {
//        return new GameMemento(gameState);
        return new GameMemento(new GameState(gameState.getHealth(), gameState.getKilledMonsters()));
    }

    public void loadGame(GameMemento memento) {
        gameState = memento.getGameState();
    }
}

class Caretacker {
    private GameOriginator game = new GameOriginator();
    private Stack<GameMemento> quickSaves = new Stack<>();

    public void shutThisDumbAss() {
        game.play();
    }

    public void F5() {
        quickSaves.push(game.saveGame());
    }

    public void F9() {
        game.loadGame(quickSaves.peek());
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        Caretacker caretacker = new Caretacker();
        caretacker.F5();
        caretacker.shutThisDumbAss();
        caretacker.F5();
        caretacker.shutThisDumbAss();
        caretacker.shutThisDumbAss();
        caretacker.shutThisDumbAss();
        caretacker.shutThisDumbAss();
        caretacker.F9();
        caretacker.shutThisDumbAss();
    }
}
