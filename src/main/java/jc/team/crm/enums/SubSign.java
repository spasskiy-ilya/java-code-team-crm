package jc.team.crm.enums;

public enum SubSign {
    WAITING("Ожидание", 1);

    private final String title;
    private final int order;

    SubSign(String title, int order) {
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
