package patterns.behavioral.mediator;

import java.util.Scanner;

class Brain {
    private Ear ear;
    private Eye eye;
    private Face face;
    private Hand hand;
    private Leg leg;

    Brain() {
        createBodyParts();
    }

    private void createBodyParts() {
        ear = new Ear(this);
        eye = new Eye(this);
        face = new Face(this);
        hand = new Hand(this);
        leg = new Leg(this);
    }

    public void smgHappenedWithBodyPart(BodyPart bodyPart) {
        if (bodyPart instanceof Ear) {
            String heardSound = ((Ear) bodyPart).getSounds();
            if (heardSound.contains("stupid")) {
                leg.stepForward();
                hand.hitPersonNearYou();
                leg.kick();
            } else if (heardSound.contains("cool")) {
                face.smile();
            }
        } else if (bodyPart instanceof Eye) {
//            todo
        } else if (bodyPart instanceof Hand) {
            boolean hurting = ((Hand) bodyPart).doesItHurt();
            boolean nice = ((Hand) bodyPart).isItNice();
            if (hurting) {
                leg.stepBack();
            }
            if (nice) {
                leg.stepForward();
                hand.embraceAround();
            }
        } else if (bodyPart instanceof Leg) {
//            todo
        }
    }

    public Ear getEar() {
        return ear;
    }

    public Eye getEye() {
        return eye;
    }

    public Face getFace() {
        return face;
    }

    public Hand getHand() {
        return hand;
    }

    public Leg getLeg() {
        return leg;
    }
}

abstract class BodyPart {
    private Brain brain;

    BodyPart(Brain brain) {
        this.brain = brain;
    }

    public void changed() {
        brain.smgHappenedWithBodyPart(this);
    }
}

class Ear extends BodyPart {
    private String sounds = "";

    Ear(Brain brain) {
        super(brain);
    }

    public void hearSomething() {
        System.out.println("Enter what you hear:");
        sounds = new Scanner(System.in).next();
        changed();
    }

    public String getSounds() {
        return sounds;
    }
}

class Face extends BodyPart {

    Face(Brain brain) {
        super(brain);
    }

    public void smile() {
        System.out.println("FACE: smiling...");
    }
}

class Eye extends BodyPart {
    private String looking = "";

    Eye(Brain brain) {
        super(brain);
    }

    public void seeAround() {
        System.out.println("Enter what you see:");
        looking = new Scanner(System.in).next();
        changed();
    }

    public String getLooking() {
        return looking;
    }
}

class Hand extends BodyPart {
    private boolean isSoft;
    private boolean isHurting;

    Hand(Brain brain) {
        super(brain);
    }

    public void feelAround() {
        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.print("What you feel is soft? (yes/no)");
        if (scanner.hasNext()) {
            answer = scanner.next();
            if (answer.toLowerCase().startsWith("y")) {
                isSoft = true;
            }
        }
        System.out.print("What you feel is hurting? (yes/no)");
        if (scanner.hasNext()) {
            answer = scanner.next();
            if (answer.toLowerCase().startsWith("y")) {
                isHurting = true;
            }
        }
        changed();
    }

    public void hitPersonNearYou() {
        System.out.println("HAND: just hit offender!");
    }

    public void embraceAround() {
        System.out.println("HAND: Embracing what is in front of you...");
    }

    public boolean doesItHurt() {
        return isHurting;
    }

    public boolean isItNice() {
        return !isHurting && isSoft;
    }
}

class Leg extends BodyPart {

    Leg(Brain brain) {
        super(brain);
    }

    public void kick() {
        System.out.println("LEG: Just kicked offender in front of you..");
    }

    public void stepBack() {
        System.out.println("LEG: Stepping back...");
    }

    public void stepForward() {
        System.out.println("LEG: Stepping forward...");
    }
}


public class MediatorDemo {
    public static void main(String[] args) {
        Brain human = new Brain();
        Scanner scann = new Scanner(System.in);
        String input;

        askingPrompt();
        while (scann.hasNext()) {
            input = scann.next();
            switch (input) {
                case "Ear":
                    human.getEar().hearSomething();
                    break;
                case "Eye":
                    human.getEye().seeAround();
                    break;
                case "Hand":
                    human.getHand().feelAround();
                    break;
                case "q":
                    System.out.println("Good bye");
                    System.exit(1);
            }
            askingPrompt();
        }
        System.out.println("You have input correct input");
    }

    private static void askingPrompt() {
        System.out.print("Enter body part ('Ear','Eye','Hand' (q for quit): ");
    }
}
