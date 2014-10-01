package net.wazim.jordan.domain;

public class BluRay {

    private final String name;
    private final String price;
    private final boolean isOwned;

    public BluRay(String name, String price, boolean isOwned) {
        this.name = name;
        this.price = price;
        this.isOwned = isOwned;
    }
    public String name() {
        return name;
    }

    public String price() {
        return price;
    }

    public boolean isOwned() {
        return isOwned;
    }

}
