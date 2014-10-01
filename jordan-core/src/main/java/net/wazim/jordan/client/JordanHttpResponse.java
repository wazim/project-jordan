package net.wazim.jordan.client;

public class JordanHttpResponse {

    private Exception e;
    private int responseCode;
    private String responseBody;

    public JordanHttpResponse(int responseCode, String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public JordanHttpResponse(Exception e) {
        this.e = e;
    }

    public boolean hasException(){
        return e != null;
    }

    public Exception getException() {
        return e;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
