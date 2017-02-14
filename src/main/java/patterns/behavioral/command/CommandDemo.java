package patterns.behavioral.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Team {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public void completeProject(List<Requirement> requirements) {
        analyzeRequirements(requirements);
        for (Requirement requirement : requirements) {
            workOnRequirement(requirement);
        }
    }

    private void workOnRequirement(Requirement requirement) {
        System.out.printf("User Story (%s) has been completed%n", requirement.getUserStory());
    }

    private void analyzeRequirements(List<Requirement> requirements) {
        for (Requirement requirement : requirements) {
            if (requirement.getUserStory().isEmpty()) {
                throw new IllegalArgumentException("not enough information on some of the requirements...");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Requirement {
    private String userStory;

    public Requirement(String userStory) {
        this.userStory = userStory;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }
}

interface ICommand {
    void execute();
}

class YouAsProjectManager implements ICommand {
    private Team team;
    private List<Requirement> requirements;

    public YouAsProjectManager(Team team, List<Requirement> requirements) {
        this.team = team;
        this.requirements = requirements;
    }

    @Override
    public void execute() {
        team.completeProject(requirements);
    }

    public Team getTeam() {
        return team;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}

class HeroDeveloperCommand implements ICommand {
    private HeroDeveloper heroDeveloper;
    private String projectName;

    public HeroDeveloperCommand(HeroDeveloper heroDeveloper, String projectName) {
        this.heroDeveloper = heroDeveloper;
        this.projectName = projectName;
    }

    @Override
    public void execute() {
        heroDeveloper.doAllHardWork(projectName);
    }
}

class HeroDeveloper {
    public void doAllHardWork(String projectName) {
        System.out.printf("Hero developer completed project (%s) without requirements in manner of couple of hours!", projectName);
    }
}

class Customer {
    private List<ICommand> commands;

    public Customer() {
        commands = new ArrayList<>();
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }

    public void signContractWithBoss() {
        for (ICommand command : commands) {
            command.execute();
        }
    }
}

public class CommandDemo {
    public static void main(String[] args) {
        Customer customer = new Customer();

        Team team = new Team("X team");
        List<Requirement> requirements = Arrays.asList(new Requirement("Cool web site"), new Requirement("Ability to book beer on site"));
        ICommand commandX = new YouAsProjectManager(team, requirements);
        customer.addCommand(commandX);

        HeroDeveloper heroDeveloper = new HeroDeveloper();
        ICommand commandA = new HeroDeveloperCommand(heroDeveloper, "projectA");
        customer.addCommand(commandA);

        customer.signContractWithBoss();
    }
}
