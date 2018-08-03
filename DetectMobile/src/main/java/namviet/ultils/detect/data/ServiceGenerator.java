package namviet.ultils.detect.data;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient okHttpClient = null;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(WSConfig.HOST);


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(unitHttpClient(okHttpClient)).build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient unitHttpClient(OkHttpClient httpClient) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Content-Type", "Application/JSON").build();
                                    return chain.proceed(request);
                                }
                            }).addInterceptor(new LoggingInterceptor()).build();
        }
        return httpClient;
    }
}
