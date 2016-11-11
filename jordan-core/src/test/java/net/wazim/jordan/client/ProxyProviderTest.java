package net.wazim.jordan.client;

import org.junit.Test;

import java.net.Proxy;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ProxyProviderTest {

    @Test
    public void retrieveAProxyAndCacheIt() throws Exception {
        Proxy proxy = ProxyProvider.getProxy();
        Proxy secondProxy = ProxyProvider.getProxy();
        assertThat(proxy, equalTo(secondProxy));
    }

}