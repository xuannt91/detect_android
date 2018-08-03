package namviet.ultils.detect.data;

import java.util.HashMap;
import java.util.Map;

import namviet.ultils.detect.StringUtils;
import namviet.ultils.detect.config.Constanst;

public class Parameter {
    public static Map<String, Object> clientApp() {
        Map<String, Object> map = new HashMap<>();
        map.put(WSConfig.KeyParam.UTM_SOURCE, Constanst.KEY_UTM_SOURCE);
        map.put(WSConfig.KeyParam.UTM_MEDIUM, Constanst.KEY_UTM_MEDIUM);
        map.put(WSConfig.KeyParam.CHECKSUM, StringUtils.md5(Constanst.KEY_UTM_SOURCE + Constanst.KEY_SECRET + Constanst.KEY_UTM_MEDIUM));
        return map;
    }

    public static Map<String, Object> receiveClientDetect(String mobile,String cookie) {
        Map<String, Object> map = new HashMap<>();
        map.put(WSConfig.KeyParam.UTM_SOURCE, Constanst.KEY_UTM_SOURCE);
        map.put(WSConfig.KeyParam.UTM_MEDIUM, Constanst.KEY_UTM_MEDIUM);
        map.put(WSConfig.KeyParam.MOBILE, mobile);
        map.put(WSConfig.KeyParam.COOKIES, cookie);
        map.put(WSConfig.KeyParam.CHECKSUM, StringUtils.md5(Constanst.KEY_UTM_SOURCE + Constanst.KEY_SECRET + Constanst.KEY_UTM_MEDIUM));
        return map;
    }
}
