package jc.team.crm.enums;

public enum SubSecondPartEducation {
    KAFKA("Kafka", 1),
    DB("База данных", 2),
    SYSTEM_DESIGN("Архитектура систем", 3);

    private final String title;
    private final int order;

    SubSecondPartEducation(String title, int order) {
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
