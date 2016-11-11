package net.wazim.jordan.client;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.ExecutionException;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.net.Proxy.Type.HTTP;
import static java.util.concurrent.TimeUnit.MINUTES;

class ProxyProvider {

    private static final LoadingCache<String, Proxy> activeProxy = newBuilder()
            .expireAfterAccess(5, MINUTES)
            .build(
                    new CacheLoader<String, Proxy>() {
                        public Proxy load(String key) throws JSONException {
                            RestTemplate restTemplate = new RestTemplate();
                            ResponseEntity<String> response = restTemplate.getForEntity("http://gimmeproxy.com/api/getProxy", String.class);
                            JSONObject jsonObject = new JSONObject(response.getBody());
                            return new Proxy(HTTP,
                                    new InetSocketAddress(
                                            jsonObject.getString("ip"),
                                            jsonObject.getInt("port")
                                    ));
                        }
                    }
            );

    static Proxy getProxy() {
        try {
            return activeProxy.get("");
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
