package comq.example.yigit.challaneepigra.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comq.example.yigit.challaneepigra.Network.ApiService;
import comq.example.yigit.challaneepigra.R;
import comq.example.yigit.challaneepigra.data.model.Serializers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yigit on 23.01.2018.
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    @BindView(R.id.searchButton)
    Button searchButton;

    private Context mContext;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        mContext = getApplicationContext();
    }

    @OnClick(R.id.searchButton)
    void searchButtonClicked(){
        createApiService().
                getAnswers().
                enqueue(new Callback<List<Serializers>>() {
                    @Override
                    public void onResponse(Call<List<Serializers>> call,
                                           Response<List<Serializers>> response) {
                        if (response.isSuccessful()) {


                            progressDialogDismiss();
                            Intent myIntent = new Intent(mContext, FullList.class);
                            ArrayList<Serializers> list = new ArrayList<>();
                            list.addAll(response.body());
                            myIntent.putParcelableArrayListExtra("RESULT", list);
                            startActivity(myIntent);
                        } else {
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Serializers>> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });
    }
    private ApiService createApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getString(R.string.albumBaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();

        return retrofit.create(ApiService.class);
    }


    public void progressDialogDismiss(){

        progressDialog.dismiss();

    }

}










