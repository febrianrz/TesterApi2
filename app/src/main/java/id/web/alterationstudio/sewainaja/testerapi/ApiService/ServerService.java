package id.web.alterationstudio.sewainaja.testerapi.ApiService;


import id.web.alterationstudio.sewainaja.testerapi.Response.GetApi;
import id.web.alterationstudio.sewainaja.testerapi.Response.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by febrian on 11/22/16.
 */
public interface ServerService {
    @FormUrlEncoded
    @POST("v1/register")
    Call<Register> postRegister(@Field("email") String email,
                                @Field("username") String username,
                                @Field("password") String password,
                                @Field("api_key") String api_key);

    @FormUrlEncoded
    @POST("v1/getappkey")
    Call<GetApi> getAppKey(@Field("old_key") String old_key);
}
