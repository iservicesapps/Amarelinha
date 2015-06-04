package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttf");
        Typeface chalkduster = Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);
        TextView horarioTv = (TextView) findViewById(R.id.horarioTv);
        horarioTv.setTypeface(chalkduster);
        TextView horarioDescTv = (TextView) findViewById(R.id.horarioDescTv);
        horarioDescTv.setTypeface(chalkboard);

        // TODO change button typeface
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:923567288"));
        startActivity(intent);
    }
}