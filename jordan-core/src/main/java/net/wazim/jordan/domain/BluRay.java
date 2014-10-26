package net.wazim.jordan.domain;

public class BluRay {

    private final String name;
    private final double priceNew;
    private final double priceUsed;
    private final boolean isOwned;
    private String url;

    public BluRay(String name, double priceNew, double priceUsed, String bluRayUrl, boolean isOwned) {
        this.name = name;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.url = bluRayUrl;
        this.isOwned = isOwned;
    }

    public String getName() {
        return name;
    }

    public double getPriceNew() {
        return priceNew;
    }

    public double getPriceUsed() {
        return priceUsed;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    public String getUrl() {
        return url;
    }
}
