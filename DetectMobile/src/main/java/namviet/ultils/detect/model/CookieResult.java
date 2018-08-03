package namviet.ultils.detect.model;


import org.json.JSONArray;
import org.json.JSONObject;

public class CookieResult {
    private String mobile;
    private JSONArray cookie;

    public CookieResult(String mobile, JSONArray cookie) {
        this.mobile = mobile;
        this.cookie = cookie;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public JSONArray getCookie() {
        return cookie;
    }

    public void setCookie(JSONArray cookie) {
        this.cookie = cookie;
    }
}
