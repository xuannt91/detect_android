package namviet.ultils.detect.data;

public class WSConfig {
    public static final String HOST = "http://nvgate.vn/analytics/";

    public static final String CLIENT_APP = HOST + "clientApp";
    public static final String RECEIVE_CLIENT_DETECT = HOST + "receiveClientDetect";

    public class KeyParam {
        public static final String UTM_SOURCE = "utm_source";
        public static final String UTM_MEDIUM = "utm_medium";
        public static final String CHECKSUM = "checksum";
        public static final String MOBILE = "mobile";
        public static final String COOKIES = "cookies";
        public static final String PACKAGE_NAME = "package_name";
        public static final String PACKAGE_CODE = "package_code";
        public static final String PLATFORM_OS = "platform_os";
        public static final String PLATFORM_VERSION = "platform_version";
    }
}
