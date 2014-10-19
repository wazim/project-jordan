package net.wazim.jordan.properties;

import java.net.URI;

import static java.net.URI.create;

public class JordanProductionProperties implements JordanProperties {

    public static final URI AMAZON_BASE_URL = create("http://www.amazon.co.uk/");
    public static final URI AMAZON_QUERY_URL = create("http://www.amazon.co.uk/s/ref=sr_pg_1?rh=n%3A283926%2Cn%3A%21573408%2Cn%3A%21712388%2Cn%3A293962011%2Cp_n_binding_browse-bin%3A383380011%2Cp_36%3A-850&page=1&bbn=293962011&ie=UTF8&qid=1413304845");

    @Override
    public URI getRequestUrl() {
        return AMAZON_QUERY_URL;
    }
}
