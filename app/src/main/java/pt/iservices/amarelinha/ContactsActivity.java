package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

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
        TextView share = (TextView) findViewById(R.id.share);
        share.setTypeface(chalkduster);
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:923567288"));
        startActivity(intent);
    }

    // TODO check if has Facebook page
    public void openFacebook(View v) {
        /*Intent i;
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<id_here>"));
        } catch (Exception e) {
            i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<user_name_here>"));
        }

        startActivity(i);*/
    }

    public void openInstagram(View v) {
        Uri uri = Uri.parse("http://instagram.com/_u/amarelinha_hamburguers");
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");

        if (isIntentAvailable(this, insta)) {
            startActivity(insta);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/amarelinha_hamburguers")));
        }
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}