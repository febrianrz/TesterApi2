package id.web.alterationstudio.sewainaja.testerapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.web.alterationstudio.sewainaja.testerapi.ApiService.ServerService;
import id.web.alterationstudio.sewainaja.testerapi.Config.Config;
import id.web.alterationstudio.sewainaja.testerapi.Response.GetApi;
import id.web.alterationstudio.sewainaja.testerapi.Response.Register;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetApiActivity extends AppCompatActivity implements View.OnClickListener, Callback<GetApi> {

    Button getApiButton;
    TextView api_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_api);

        api_key = (TextView) findViewById(R.id.api_key);
        getApiButton = (Button) findViewById(R.id.getApiButton);
        getApiButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String oldKey = "5d2f796a38ad4d0a1e44b2296bd62c31";
        Config config = new Config();
        String strApi = config.getApiKey();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(config.getBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerService serverService = retrofit.create(ServerService.class);
        Call<GetApi> registerCall = serverService.getAppKey(oldKey);
        registerCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<GetApi> call, Response<GetApi> response) {
        GetApi getApi = response.body();
        api_key.setText(getApi.getApiKey().toString());
    }

    @Override
    public void onFailure(Call<GetApi> call, Throwable t) {

    }
}
