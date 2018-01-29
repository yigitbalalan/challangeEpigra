package comq.example.yigit.challaneepigra.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import comq.example.yigit.challaneepigra.R;

public class SplashScreen extends AppCompatActivity {


    private GifImageView gifImageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        gifImageView = (GifImageView) findViewById(R.id.gifImage1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(progressBar.VISIBLE);


        try{
            InputStream inputStream = getAssets().open("giphy.gif");
            byte [] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();



        } catch (IOException e) {
            e.printStackTrace();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,SearchActivity.class));
                SplashScreen.this.finish();
            }
        },3000);

    }
}
