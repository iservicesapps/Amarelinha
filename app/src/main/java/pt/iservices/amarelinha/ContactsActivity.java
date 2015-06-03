package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttf");
        Typeface chalkduster = Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);
        TextView companyDescTv = (TextView) findViewById(R.id.companyDescTv);
        companyDescTv.setTypeface(chalkboard);
        companyDescTv.setMovementMethod(new ScrollingMovementMethod());
        TextView horarioTv = (TextView) findViewById(R.id.horarioTv);
        horarioTv.setTypeface(chalkduster);
        TextView horarioDescTv = (TextView) findViewById(R.id.horarioDescTv);
        horarioDescTv.setTypeface(chalkboard);
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:923567288"));
        startActivity(intent);
    }
}