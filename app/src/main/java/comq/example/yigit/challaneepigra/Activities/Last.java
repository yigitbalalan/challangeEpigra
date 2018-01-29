package comq.example.yigit.challaneepigra.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import comq.example.yigit.challaneepigra.R;

/**
 * Created by yigit on 25.01.2018.
 */

public class Last extends AppCompatActivity {

    private ImageView img1;
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        mContext = getApplicationContext();


        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();

        String url = myBundle.getString("url");

        Picasso.with(mContext).load(url).into(photoView);






    }


}