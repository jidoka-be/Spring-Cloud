package be.jidoka.customer.domain.company;

public enum NumberOfEmployees {

    LESS_THEN_10("1-9"),
    LESS_THEN_50("10-49"),
    LESS_THEN_100("50-99"),
    LESS_THEN_500("100-499"),
    LESS_THEN_1000("500-999"),
    LESS_THEN_2500("1000-2499"),
    LESS_THEN_10000("2500-9999"),
    LESS_THEN_100000("10000-99999"),
    MORE_THEN_100000(">100000");

    private final String description;

    NumberOfEmployees(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
