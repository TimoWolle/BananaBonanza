package de.bananabonanza.enumeration;

public enum ProductCategory {
    FRUITS("Fruits"),
    CLOTHING("Clothing"),
    HOME_DECOR("Home Decor"),
    TOYS("Toys"),
    GADGETS("Gadgets"),
    ACCESSORIES("Accessories"),
    BEAUTY("Beauty"),
    BOOKS("Books"),
    FOOD_AND_DRINKS("Food & Drinks"),
    SPORTS_AND_OUTDOORS("Sports & Outdoors");

    private String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

