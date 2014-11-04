package net.wazim.jordan.domain;

public class BluRay {

    private final String name;
    private double priceNew;
    private double priceUsed;
    private boolean isInteresting;
    private final int rating;
    private String url;
    private int id;

    public BluRay(String name, double priceNew, double priceUsed, String bluRayUrl, boolean isInteresting, int rating) {
        this.name = name;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.url = bluRayUrl;
        this.isInteresting = isInteresting;
        this.rating = rating;
        this.id = name.hashCode();
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

    public int getRating() {
        return rating;
    }

    public String getUrl() {
        return url;
    }

    public void setInteresting(boolean interesting) {
        this.isInteresting = interesting;
    }

    public void setPriceNew(double priceNew) {
        this.priceNew = priceNew;
    }

    public void setPriceUsed(double priceUsed) {
        this.priceUsed = priceUsed;
    }

    public int getId() {
        return id;
    }
}
