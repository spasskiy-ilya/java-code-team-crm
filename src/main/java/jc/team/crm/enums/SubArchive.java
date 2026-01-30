package jc.team.crm.enums;

public enum SubArchive {
    ARCHIVE("Архив", 1);

    private final String title;
    private final int order;

    SubArchive(String title, int order) {
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
