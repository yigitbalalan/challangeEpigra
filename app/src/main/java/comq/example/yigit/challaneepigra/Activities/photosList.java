package comq.example.yigit.challaneepigra.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import comq.example.yigit.challaneepigra.R;
import comq.example.yigit.challaneepigra.data.model.photosResult;


/**
 * Created by yigit on 24.01.2018.
 */

public class photosList extends AppCompatActivity implements OnClickListener {
    private ArrayList<photosResult> myList1;
    private SimpleRecyclerAdapter adapter;
    public OnClickListener imageClickListener;
    public Context mContext;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos);



        mContext = getApplication();
        myList1 = (ArrayList<photosResult>) getIntent().getSerializableExtra("PHOTOS");
        final RecyclerView liste = (RecyclerView)findViewById(R.id.liste);

        if(mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            GridLayoutManager layoutManager
                    = new GridLayoutManager(this, 2);
            liste.setLayoutManager(layoutManager);
            adapter = new SimpleRecyclerAdapter(myList1,R.layout.grid_rv,this);
            liste.setAdapter(adapter);

        }else{
            GridLayoutManager layoutManager = new GridLayoutManager(this,3);
            liste.setLayoutManager(layoutManager);
            adapter = new SimpleRecyclerAdapter(myList1,R.layout.grid_rv,this);
            liste.setAdapter(adapter);
        }






        //Type type = new TypeToken<List<Serializers>>(){}.getType();

        //for(int i=0;i<myList1.size();i++) {
        //searchHistory.add(myList1.get(i).getTitle());

        //}



    }


    @Override
    public void onImageClick(String imageData) {
        Intent myIntent = new Intent(photosList.this,Last.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("url",imageData);

        myIntent.putExtras(myBundle);
        startActivity(myIntent);



    }
}
