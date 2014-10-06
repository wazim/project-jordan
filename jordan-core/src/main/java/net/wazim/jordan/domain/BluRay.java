package net.wazim.jordan.domain;

public class BluRay {

    private final String name;
    private final String priceNew;
    private final String priceUsed;
    private final boolean isOwned;

    public BluRay(String name, String priceNew, String priceUsed, boolean isOwned) {
        this.name = name;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.isOwned = isOwned;
    }
    public String name() {
        return name;
    }

    public String priceNew() {
        return priceNew;
    }

    public String priceUsed() {
        return priceUsed;
    }

    public boolean isOwned() {
        return isOwned;
    }

}
