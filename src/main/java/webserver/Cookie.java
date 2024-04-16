package webserver;

import java.util.HashMap;
import java.util.Map;

public class Cookie {
    private final Map<String, String> cookies;

    public Cookie() {
        this.cookies = new HashMap<>();
    }

    public Cookie(String cookies) {
        this();
        String[] cookieArray = cookies.split(";");
        for (String cookie : cookieArray) {
            String[] keyValue = cookie.split("=");
            this.cookies.put(keyValue[0].trim(), keyValue[1].trim());
        }
    }

    public void setCookie(String key, String value) {
        cookies.put(key, value);
    }

    public String getCookie(String key) {
        return cookies.get(key);
    }

    public void deleteCookie(String key) {
        cookies.remove(key);
    }

    @Override
    public String toString() {
        if (cookies.isEmpty()) {
            return "";
        }
        return "Set-Cookie: " + cookies.entrySet()
            .stream()
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .reduce((cookie1, cookie2) -> cookie1 + "; " + cookie2)
            .orElse("") + "\r\n";
    }
}
