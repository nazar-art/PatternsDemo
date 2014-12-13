package patterns.behavioral.templateMethod;

import java.util.Date;

abstract class MessageSearcher {
    private Date date;
    private String personName;
    private ImportanceLevel importanceLevel;

    MessageSearcher(Date date, String personName, ImportanceLevel importanceLevel) {
        this.date = date;
        this.personName = personName;
        this.importanceLevel = importanceLevel;
    }

    // general operations
    protected void apllyDateCriteria() {
        System.out.println("Standard date criteria has been applied.");
    }

    protected void applySendPersonCriteria() {
        System.out.println("Standard person criteria has been applied.");
    }

    protected void applyImportantCriteria() {
        System.out.println("Standard importance criteria has been applied.");
    }

    // template method
    public String search() {
        apllyDateCriteria();
        applySendPersonCriteria();
        System.out.println("Template method does some verification accordingly to search algorithm.");
        applyImportantCriteria();
        System.out.println("Template method verifies if message could be so important or useless from person provided in criteria.\n");
        return "some list of messages";
    }
}

class ImportantMessagesSearcher extends MessageSearcher {

    ImportantMessagesSearcher(Date date, String personName) {
        super(date, personName, ImportanceLevel.IMPORTANT);
    }

    @Override
    protected void applyImportantCriteria() {
        System.out.println("Special importance criteria has been formed: IMPORTANT");
    }
}

class UselessMessagesSearcher extends MessageSearcher {

    UselessMessagesSearcher(Date date, String personName) {
        super(date, personName, ImportanceLevel.USELESS);
    }

    @Override
    protected void applyImportantCriteria() {
        System.out.println("Special importance criteria has been formed: USELESS");
    }
}

enum ImportanceLevel {
    IMPORTANT, MEDIUM, USELESS, INFO
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        MessageSearcher searcher = new UselessMessagesSearcher(new Date(), "Harry");
        searcher.search();
        searcher = new ImportantMessagesSearcher(new Date(), "Cay");
        searcher.search();
    }
}
