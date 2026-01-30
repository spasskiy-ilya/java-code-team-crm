package jc.team.crm.enums;

public enum SubSearch {
    SEARCH("Поиск", 1),
    STOP_SEARCH("Приостановлен поиск", 2),
    RESEARCH("Исследование", 3);

    private final String title;
    private final int order;

    SubSearch(String title, int order) {
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
