package de.bananabonanza.enumeration;

public enum ProductStatus {
    AVAILABLE("Available"),
    OUT_OF_STOCK("Out of Stock"),
    DISCONTINUED("Discontinued"),
    PREORDER("Preorder"),
    BACKORDER("Backorder"),
    AVAILABLE_WITH_STOCK("Available. Stock: %d");

    private String resourceKey;

    ProductStatus(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
