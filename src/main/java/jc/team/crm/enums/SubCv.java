package jc.team.crm.enums;

public enum SubCv {
    CV("Резюме", 1),
    LEGEND("Легенда", 2);

    private final String title;
    private final int order;

    SubCv(String title, int order) {
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
