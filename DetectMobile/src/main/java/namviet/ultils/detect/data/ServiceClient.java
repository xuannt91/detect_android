package namviet.ultils.detect.data;

import java.util.Map;

import io.reactivex.Observable;
import namviet.ultils.detect.config.Constanst;
import namviet.ultils.detect.model.MobileResponse;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ServiceClient {

    @GET(WSConfig.CLIENT_APP)
    Observable<MobileResponse> clientApp(@QueryMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST(WSConfig.RECEIVE_CLIENT_DETECT)
    Observable<MobileResponse> receiveClientDetect(@Field(WSConfig.KeyParam.UTM_MEDIUM) String utm_medium, @Field(WSConfig.KeyParam.UTM_SOURCE) String utm_source,
                                                   @Field(WSConfig.KeyParam.MOBILE) String mobile, @Field(WSConfig.KeyParam.COOKIES) String cookies,
                                                   @Field(WSConfig.KeyParam.CHECKSUM) String checksum, @Field(WSConfig.KeyParam.PACKAGE_NAME) String packageName,
                                                   @Field(WSConfig.KeyParam.PACKAGE_CODE) String packageCode, @Field(WSConfig.KeyParam.PLATFORM_OS) String os,
                                                   @Field(WSConfig.KeyParam.PLATFORM_VERSION) String version);

    @GET
    Observable<MobileResponse> getHeader(@Url String url);
}
