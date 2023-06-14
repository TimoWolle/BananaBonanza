package de.bananabonanza.enumeration;

public enum Rating {
    ONE_STAR(1, "Ein Stern"),
    TWO_STARS(2, "Zwei Sterne"),
    THREE_STARS(3, "Drei Sterne"),
    FOUR_STARS(4, "Vier Sterne"),
    FIVE_STARS(5, "Fünf Sterne");

    private final int value;
    private final String description;

    Rating(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
