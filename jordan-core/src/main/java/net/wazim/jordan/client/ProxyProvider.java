package net.wazim.jordan.client;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;

class ProxyProvider {

    private static final LoadingCache<String, HttpHost> activeProxy = newBuilder()
            .expireAfterAccess(5, MINUTES)
            .build(
                    new CacheLoader<String, HttpHost>() {
                        public HttpHost load(String key) throws JSONException {
                            RestTemplate restTemplate = new RestTemplate();
                            ResponseEntity<String> response = restTemplate.getForEntity("http://gimmeproxy.com/api/getProxy?supportsHttps=true&maxCheckPeriod=1000?cookies=true?user-agent=true", String.class);
                            JSONObject jsonObject = new JSONObject(response.getBody());
                            return new HttpHost(
                                    jsonObject.getString("ip"),
                                    jsonObject.getInt("port"),
                                    "http"
                            );
                        }
                    }
            );

    static HttpHost getProxy() {
        try {
            return activeProxy.get("");
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
