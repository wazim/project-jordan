package net.wazim.jordan.properties;

import java.net.URI;

import static java.net.URI.create;

public class JordanProductionProperties implements JordanProperties {

    public static final URI AMAZON_BASE_URL = create("http://www.amazon.co.uk/");
    public static final URI AMAZON_QUERY_URL = create("http://www.amazon.co.uk/s/ref=sr_adv_d?__mk_en_GB=%C5M%C5Z%D5%D1&search-alias=dvd&unfiltered=1&field-keywords=&field-title=&field-actor=&field-director=&field-label=&field-ean=&field-price=0-300&field-intended_use_browse-bin=&field-binding_browse-bin=383380011&node=&field-theme_browse-bin=&field-dvd-region=&emi=&sort=&Adv-Srch-DVD-Submit.x=61&Adv-Srch-DVD-Submit.y=17");

    @Override
    public URI getRequestUrl() {
        return AMAZON_QUERY_URL;
    }
}
