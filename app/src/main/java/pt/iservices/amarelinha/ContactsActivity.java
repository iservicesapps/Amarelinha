package pt.iservices.amarelinha;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mariocosme on 02/06/15.
 */

public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        setUpLayout();
    }

    private void setUpLayout() {
        // TODO chalkboard doens't work (maybe .ttf?)
        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttc");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboard);
    }

    public void call(View v) {
        Log.d("teste", "call xpto");
    }
}