package net.wazim.jordan.domain;

public class BluRay {

    private final String name;
    private final double priceNew;
    private final double priceUsed;
    private boolean isInteresting;
    private String url;

    public BluRay(String name, double priceNew, double priceUsed, String bluRayUrl, boolean isInteresting) {
        this.name = name;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.url = bluRayUrl;
        this.isInteresting = isInteresting;
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

    public boolean getIsInteresting() {
        return isInteresting;
    }

    public String getUrl() {
        return url;
    }

    public void setInteresting(boolean interesting) {
        this.isInteresting = interesting;
    }
}
