package net.wazim.jordan.properties;

import java.net.URI;

import static java.net.URI.create;

public class JordanTestSpecificProperties implements JordanProperties {

    public static final URI AMAZON_BASE_URL = create("http://localhost:11511/amazon");
    public static final URI AMAZON_QUERY_URL = create("http://localhost:11511/amazon/bluray");

    @Override
    public URI getRequestUrl() {
        return AMAZON_QUERY_URL;
    }
}
