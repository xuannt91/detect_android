package namviet.ultils.detect;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import namviet.ultils.detect.config.Constanst;
import namviet.ultils.detect.data.DeviceUtils;
import namviet.ultils.detect.data.Parameter;
import namviet.ultils.detect.data.ServiceClient;
import namviet.ultils.detect.data.ServiceGenerator;
import namviet.ultils.detect.listener.DetectListener;
import namviet.ultils.detect.model.CookieResult;
import namviet.ultils.detect.model.MobileResponse;

public class MobileUtils extends Observable {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Scheduler scheduler;
    private DetectListener detectListener;
    private Context mContext;
    private String mobile = "";

    public MobileUtils(Context context, DetectListener detectListener) {
        this.mContext = context;
        this.detectListener = detectListener;
        this.scheduler = Schedulers.io();
    }

    public void detectMobile() {
        ServiceClient apiService = ServiceGenerator.createService(ServiceClient.class);
        Disposable disposable = apiService.clientApp(Parameter.clientApp())
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileResponse>() {
                    @Override
                    public void accept(MobileResponse mobileResponse) throws Exception {
                        if (null != mobileResponse && mobileResponse.getError().equals(Constanst.KEY_DETECT_URL)) {
                            getDetectUrl(mobileResponse.getData().getDetect_url());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

        compositeDisposable.add(disposable);
    }


    private void getDetectUrl(String link) {
        new AsyncDetect(link).execute();
    }

    class AsyncDetect extends AsyncTask<Void, Void, CookieResult> {
        private String link;

        public AsyncDetect(String link) {
            this.link = link;
        }

        @Override
        protected CookieResult doInBackground(Void... voids) {
            CookieResult cookieResult = null;
            JSONArray map = new JSONArray();

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(link);
                request.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.1; ASUS_X00DD Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Mobile Safari/537.36");
                HttpResponse response = client.execute(request);
                List<org.apache.http.Header> httpHeaders = Arrays.asList(response.getAllHeaders());

                for (org.apache.http.Header header : httpHeaders) {
                    if (header.getName().equals("Set-Cookie")) {
                        JSONObject cookie = getKeyValue(header.getValue());
                        map.put(cookie);
                    }
                }
                cookieResult = new CookieResult(mobile, map);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return cookieResult;
        }

        @Override
        protected void onPostExecute(CookieResult result) {
            super.onPostExecute(result);
            if (null != result) {
                receiveClientDetect(result);
                detectListener.onDetectSuccess(result.getMobile());
            }

        }
    }

    private JSONObject getKeyValue(String cookie) {
        String[] str1 = cookie.split(";");
        String[] str2 = str1[0].split("=");
        String[] str3 = str1[1].split("=");
        if (null != str2 && str2.length > 0 && str2[0].equals("msisdn")) {
            mobile = str2[1];
        }
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != str2 && str2.length > 0) {
                jsonObject.put(str2[0], str2[1]);
            }
            if (null != str3 && str3.length > 0) {
                jsonObject.put(str3[0], str3[1]);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void receiveClientDetect(CookieResult result) {
        ServiceClient apiService = ServiceGenerator.createService(ServiceClient.class);
        Disposable disposable = apiService.receiveClientDetect(Constanst.KEY_UTM_MEDIUM, Constanst.KEY_UTM_SOURCE, result.getMobile(), result.getCookie().toString(),
                StringUtils.md5(Constanst.KEY_UTM_SOURCE + Constanst.KEY_SECRET + Constanst.KEY_UTM_MEDIUM), mContext.getPackageName(), DeviceUtils.getPlatform(), "ANDROID", DeviceUtils.getVerisonCode())
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileResponse>() {
                    @Override
                    public void accept(MobileResponse mobileResponse) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

        compositeDisposable.add(disposable);
    }

}
