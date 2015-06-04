package pt.iservices.amarelinha;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by mariocosme on 04/06/15.
 */
public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttf");
        TextView companyDesc = (TextView) findViewById(R.id.companyDesc);
        companyDesc.setTypeface(chalkboard);
        companyDesc.setMovementMethod(new ScrollingMovementMethod());
    }
}
