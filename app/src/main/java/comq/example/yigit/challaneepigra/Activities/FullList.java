package comq.example.yigit.challaneepigra.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comq.example.yigit.challaneepigra.Network.ApiService;
import comq.example.yigit.challaneepigra.R;
import comq.example.yigit.challaneepigra.data.model.Serializers;
import comq.example.yigit.challaneepigra.data.model.photosResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yigit on 23.01.2018.
 */

public class FullList extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "FullListActivity";
    private List<String> searchHistory = new ArrayList<>();
    private ArrayAdapter<String> listViewAdapter;
    private List<Serializers> listResult;
    private ArrayList<Serializers> myList;
    private String title;
    private Context mContext;
    public ProgressDialog progressDialog;

    @BindView(R.id.listView1)
    ListView searchHistoryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full);
        ButterKnife.bind(this);
        mContext = getApplicationContext();

        progressDialog = new ProgressDialog(this);

        myList = (ArrayList<Serializers>) getIntent().getSerializableExtra("RESULT");

        createAlbumList();
        setUpView();

        //Type type = new TypeToken<List<Serializers>>(){}.getType();

        //for(int i=0;i<myList1.size();i++) {
            //searchHistory.add(myList1.get(i).getTitle());

        //}


    }

    private void createAlbumList(){
        for(int i = 0;i<myList.size();i++){
            searchHistory.add(myList.get(i).getTitle());
        }
    }

    private void setUpView() {

        listViewAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchHistory);

        searchHistoryListView.setAdapter(listViewAdapter);
        searchHistoryListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(i<=5)





            createApiService1().
                    getPhotos(myList.get(i).getId()).enqueue(new Callback<List<photosResult>>() {
                @Override
                public void onResponse(Call<List<photosResult>> call, Response<List<photosResult>> response) {
                    if (response.isSuccessful()) {

                        progressDialogDismiss();
                        Intent myIntent1 = new Intent(mContext, photosList.class);
                        ArrayList<photosResult> listPhoto = new ArrayList<>();
                        listPhoto.addAll(response.body());
                        myIntent1.putParcelableArrayListExtra("PHOTOS", listPhoto);
                        startActivity(myIntent1);


                    } else {
                        ///
                    }
                }

                @Override
                public void onFailure(Call<List<photosResult>> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);

                }
            });


    }
    private ApiService createApiService1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getString(R.string.albumBaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.BUTTON_POSITIVE);
        // show it
        progressDialog.show();


        return retrofit.create(ApiService.class);



    }


    public void progressDialogDismiss(){

        progressDialog.dismiss();

    }

}
