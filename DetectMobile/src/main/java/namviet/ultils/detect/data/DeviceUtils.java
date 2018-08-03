package namviet.ultils.detect.data;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import namviet.ultils.detect.BuildConfig;


/**
 * Created by admin on 3/2/2018.
 */

public class DeviceUtils {
    public static String getDeviceId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getPlatform() {
        int sdkVersion = Build.VERSION.SDK_INT;
        return "" + sdkVersion;
    }

    public static String getVerisonCode() {
        return "" + BuildConfig.VERSION_NAME;
    }

    public static String getModel() {
        return Build.MODEL;
    }
}
