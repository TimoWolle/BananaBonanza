package de.bananabonanza.enumeration;

public enum DeliveryStatus {
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered"),
    RETURNED("Returned"),
    DAMAGED("Damaged"),
    ON_HOLD("On Hold");

    private final String resourceKey;

    DeliveryStatus(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
