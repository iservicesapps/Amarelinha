package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;

/**
 * Created by mariocosme on 04/06/15.
 */
public class MealCard extends Activity {

    private AQuery aq;
    private int currentStamps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_card);

        aq = new AQuery(this);
        SharedPreferences prefs = getSharedPreferences("default", 0);
        currentStamps = prefs.getInt("currentStamps", 0);

        // TODO: Para apagar
        currentStamps = 2;

        setUpLayout();
    }

    private void setUpLayout() {
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);

        setCurrentStamps();
    }

    private void setCurrentStamps() {

    }

    public void insertCode(View v) {
        Log.d("teste", "insert Code!");
    }
}