package pt.iservices.amarelinha;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by mariocosme on 02/06/15.
 */
public class SplashActivity extends Activity {

    private ImageView splash;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        splash = (ImageView) findViewById(R.id.splashImage);
        rl = (RelativeLayout) findViewById(R.id.rl);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Picasso.with(this).load(R.drawable.splash).resize(rl.getMeasuredWidth(), getHeightWithRacio(R.drawable.splash, rl.getMeasuredWidth())).into(splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainMenuActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 500); // TODO change to 3500
    }

    private int getHeightWithRacio(int image, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        return (imageHeight*width/imageWidth);
    }
}