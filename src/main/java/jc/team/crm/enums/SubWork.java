package jc.team.crm.enums;

public enum SubWork {
    ON_PROJECT("На проекте", 1);

    private final String title;
    private final int order;

    SubWork(String title, int order) {
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
