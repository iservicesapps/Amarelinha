package pt.iservices.amarelinha;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
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
        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttc");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboard);
    }
}