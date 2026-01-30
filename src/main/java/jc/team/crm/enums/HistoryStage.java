package jc.team.crm.enums;

public enum HistoryStage {

    FIRST_PART_EDUCATION("Первая часть обучения", 1),
    SIGN("Подписание договора", 2),
    SECOND_PART_EDUCATION("Вторая часть обучения", 3),
    CV("Составление резюме", 4),
    PRACTICE("Практика", 5),
    SEARCH("Поиск работы", 6),
    WORK("На проекте", 7),
    DONE("Завершено", 8),
    ARCHIVE("Архив", 9);

    private final String title;
    private final int order;

    HistoryStage(String title, int order) {
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
