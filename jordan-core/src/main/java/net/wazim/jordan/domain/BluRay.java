package net.wazim.jordan.domain;

import java.math.BigDecimal;

public class BluRay {

    private final String name;
    private final BigDecimal priceNew;
    private final BigDecimal priceUsed;
    private final boolean isOwned;

    public BluRay(String name, BigDecimal priceNew, BigDecimal priceUsed, boolean isOwned) {
        this.name = name;
        this.priceNew = priceNew;
        this.priceUsed = priceUsed;
        this.isOwned = isOwned;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceNew() {
        return priceNew;
    }

    public BigDecimal getPriceUsed() {
        return priceUsed;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

}
