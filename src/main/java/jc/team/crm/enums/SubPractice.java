package jc.team.crm.enums;

public enum SubPractice {
    STREAM("Stream API", 1),
    SQL("SQL", 2),
    REVIEW("Code Review", 3);

    private final String title;
    private final int order;

    SubPractice(String title, int order) {
        this.title = title;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }
}
