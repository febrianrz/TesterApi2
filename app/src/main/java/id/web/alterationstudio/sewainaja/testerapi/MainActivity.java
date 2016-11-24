package id.web.alterationstudio.sewainaja.testerapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import id.web.alterationstudio.sewainaja.testerapi.ApiService.ServerService;
import id.web.alterationstudio.sewainaja.testerapi.Config.Config;
import id.web.alterationstudio.sewainaja.testerapi.Response.Register;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<Register> {

    Button tombolRegister;
    ProgressBar loading;
    EditText username,email,password;
    String strUsername,strEmail,strPassword;
    TextView statusServer, msgServer, keyServer, getApiKeyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tombolRegister = (Button) findViewById(R.id.tombolRegister);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        statusServer = (TextView) findViewById(R.id.statusServer);
        msgServer = (TextView) findViewById(R.id.msgServer);
        keyServer = (TextView) findViewById(R.id.keyServer);
        getApiKeyButton = (TextView) findViewById(R.id.getApiKeyButton);
        loading = (ProgressBar) findViewById(R.id.loading);

        loading.setVisibility(View.GONE);
        tombolRegister.setOnClickListener(this);
        getApiKeyButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == tombolRegister){
            loading.setVisibility(View.VISIBLE);
            strUsername = username.getText().toString();
            strEmail = email.getText().toString();
            strPassword = password.getText().toString();

            if(strUsername.isEmpty() && strEmail.isEmpty() && strPassword.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(),"Field Harus Diisi",Toast.LENGTH_LONG);
                toast.show();
            } else {
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
                Call<Register> registerCall = serverService.postRegister(strEmail,strUsername,strPassword,strApi);
                registerCall.enqueue(this);
            }
        } else if(view == getApiKeyButton){
            Intent intent = new Intent(getApplicationContext(),GetApiActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResponse(Call<Register> call, Response<Register> response) {
        Register register = response.body();
        if(register.getStatus()){
            statusServer.setText(register.getStatus().toString());
            msgServer.setText(register.getMsg().toString());
            keyServer.setText(register.getUserKey().toString());
        } else {
            statusServer.setText(register.getStatus().toString());
            msgServer.setText(register.getMsg().toString());
            keyServer.setText("");
        }

        Toast toast = null;
        toast = Toast.makeText(this.getApplicationContext(),register.getMsg(),Toast.LENGTH_LONG);
        toast.show();
        loading.setVisibility(View.GONE);

    }

    @Override
    public void onFailure(Call<Register> call, Throwable t) {
        Log.e("Gagal","gagal");
    }
}
