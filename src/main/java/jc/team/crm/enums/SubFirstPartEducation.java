package jc.team.crm.enums;

public enum SubFirstPartEducation {
    JAVA_CORE("Java Core", 1),
    COLLECTION("Коллекции", 2),
    CONCURRENCY("Многопоточность", 3),
    STREAM("Стримы", 4),
    MAVEN("Maven", 5),
    SPRING_CORE("Spring core", 6),
    SPRING_MVC("Spring mvc", 7),
    SPRING_DATA("Spring data", 8),
    SPRING_BOOT("Spring boot", 9),
    SPRING_SECURITY("Spring security", 10);

    private final String title;
    private final int order;

    SubFirstPartEducation(String title, int order) {
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
